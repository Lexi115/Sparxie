#!/usr/bin/env bash

# ANSI Codes
ANSI_BOLD="\033[1m"
ANSI_RED="\033[31m"
ANSI_GREEN="\033[32m"
ANSI_YELLOW="\033[33m"
ANSI_CYAN="\033[36m"
ANSI_MAGENTA="\033[35m"
ANSI_GRAY="\033[90m"
ANSI_RESET="\033[0m"

QUESTION="${ANSI_BOLD}${ANSI_MAGENTA}[?]${ANSI_RESET}"
SUCCESS="${ANSI_BOLD}${ANSI_GREEN}[✔]${ANSI_RESET}"
ERROR="${ANSI_BOLD}${ANSI_RED}[✖]${ANSI_RESET}"
WARNING="${ANSI_BOLD}${ANSI_YELLOW}[!]${ANSI_RESET}"
INFO="${ANSI_BOLD}${ANSI_CYAN}[i]${ANSI_RESET}"

function clear_screen() {
  clear -x || tput clear || clear
}

function input() {
  local target_var=$1
  local user_input
  read -r -p "> " user_input

  printf -v "$target_var" "%s" "$user_input"
}

function ask_question() {
  local message=$1
  local array_name=$2
  local default_choice=$3
  local return_var=$4

  local choices
  eval "choices=( \"\${${array_name}[@]}\" )"

  local array_length=${#choices[@]}

  echo -e "${QUESTION} ${ANSI_RESET}${ANSI_BOLD}$message"
  for ((i = 0 ; i < array_length ; i++)); do
    if [[ $i -eq "$default_choice" ]]; then
      echo -e "\t${ANSI_YELLOW}$i ${ANSI_GRAY} ${ANSI_RESET}${ANSI_BOLD}${choices[$i]} (default)${ANSI_RESET}"
    else
      echo -e "\t${ANSI_YELLOW}$i ${ANSI_GRAY} ${ANSI_RESET}${choices[$i]}"
    fi
  done

  local local_choice

  while true; do
    input local_choice

    if [[ -z "$local_choice" ]]; then
      local_choice="$default_choice"
      break
    fi

    if [[ "$local_choice" =~ ^[0-9]+$ ]] && [[ "$local_choice" -ge 0 ]] && [[ "$local_choice" -lt "$array_length" ]]; then
      break
    fi

    echo -e "${ERROR} ${ANSI_BOLD}Invalid input!${ANSI_RESET}"
  done

  printf -v "$return_var" "%s" "$local_choice"
}

function print_header() {
  echo -e "${ANSI_BOLD}${ANSI_RED}.-* ${ANSI_RESET}${ANSI_BOLD}Sparxie Docker Run Script ${ANSI_RED}*-."
  echo -e "\t   By ${ANSI_CYAN}Lexi115"
  echo -e "\n"
}

function exit_program() {
  echo -e "${QUESTION} ${ANSI_BOLD}Thanks for using this tool, goodbye! uwu"
  exit 0
}

function execute_run() {
  local target_var=$1
  local choice="${!target_var}"

  case "$choice" in
    1) docker compose --profile="all" up -d --build || return 1;;
    2) docker compose --profile="game" up -d --build || return 1;;
    3) docker compose --profile="user" up -d --build || return 1;;
    4) docker compose --profile="inventory" up -d --build || return 1;;
    5) docker compose --profile="gacha" up -d --build || return 1;;
    6) docker compose --profile="shop" up -d --build || return 1;;
    7) docker compose --profile="all" down --remove-orphans || return 1;;
    8) docker compose --profile="all" down --remove-orphans -v || return 1;;
    *) echo -e "${ERROR} ${ANSI_BOLD}Unknown choice!" || return 1;;
  esac
  return 0
}

function main() {
  clear_screen
  print_header

  local profile_choice=""
  local continue_choice=""
  # shellcheck disable=SC2034
  local operation_choices=('Exit' 'All services' 'Game' 'User' 'Inventory' 'Gacha' 'Shop' 'Down all services' 'Down all services (+ volumes)')
  # shellcheck disable=SC2034
  local continue_choices=('No' 'Yes')

  while true; do
    echo -e "${WARNING} ${ANSI_BOLD}Make sure that the Docker daemon is running in the background!"
    ask_question "Please choose the profile you want to run:" operation_choices 1 profile_choice
    clear_screen

    if [[ "$profile_choice" -eq 0 ]]; then
      exit_program
    fi

    echo -e "${INFO} ${ANSI_BOLD}Executing..."
    execute_run profile_choice
    status=$?
    clear_screen

    if [[ $status -eq 0 ]]; then
      echo -e "${SUCCESS} ${ANSI_BOLD}All operations completed!"
    else
      echo -e "${ERROR} ${ANSI_BOLD}An error occurred while executing this operation."
    fi

    ask_question "Would you like to perform another action?" continue_choices 1 continue_choice
    clear_screen

    if [[ "$continue_choice" -eq 0 ]]; then
      exit_program
    fi
  done
}

main
