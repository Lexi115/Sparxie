#!/usr/bin/env bash

# Variables
JWT_SECRET=$(openssl rand -base64 48 | tr -d '\n\r')
export JWT_SECRET="${JWT_SECRET}"

KAFKA_SERVER=kafka:9092
REDIS_HOST=redis
REDIS_PORT=6379
POSTGRES_HOST=postgres
POSTGRES_PORT=5432
MONGO_HOST=mongo
MONGO_PORT=27017
SUPABASE_CLIENT_URI=http://supabase:9999
SUPABASE_EXTERNAL_CLIENT_URI=http://localhost:9999

GACHA_SERVICE_BASE_URI=http://gacha:8080
GAME_SERVICE_BASE_URI=http://game:8080
INVENTORY_SERVICE_BASE_URI=http://inventory:8080
SHOP_SERVICE_BASE_URI=http://shop:8080
USER_SERVICE_BASE_URI=http://user:8080

OAUTH2_REDIRECT_URI=http://localhost:8080/api/auth/callback

# ANSI Codes
ANSI_BOLD="\033[1m"
ANSI_UNDERLINE="\033[4m"
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
  tput clear || clear
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
  eval "choices=( \"\${$array_name[@]}\" )"

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
  echo -e "${ANSI_BOLD}${ANSI_RED}.-* ${ANSI_RESET}${ANSI_BOLD}Sparxie Automatic Environment Setup ${ANSI_RED}*-."
  echo -e "\t\tBy ${ANSI_CYAN}Lexi115"
  echo -e "\n"
}

function exit_program() {
  echo -e "${QUESTION} ${ANSI_BOLD}Thanks for using this tool, goodbye! uwu"
  exit 0
}

function setup_root() {
  echo -e "${INFO} ${ANSI_BOLD}Setting '${ANSI_UNDERLINE}root${ANSI_RESET}${ANSI_BOLD}'..."
cat <<EOF > ./.env
COMPOSE_PROFILES=*

JWT_SECRET=${JWT_SECRET}

GOOGLE_CLIENT_ID=
GOOGLE_CLIENT_SECRET=
GOOGLE_CLIENT_REDIRECT_URI=${OAUTH2_REDIRECT_URI}/google

GITHUB_CLIENT_ID=
GITHUB_CLIENT_SECRET=
GITHUB_CLIENT_REDIRECT_URI=${OAUTH2_REDIRECT_URI}/github

DISCORD_CLIENT_ID=
DISCORD_CLIENT_SECRET=
DISCORD_CLIENT_REDIRECT_URI=${OAUTH2_REDIRECT_URI}/discord

TWITCH_CLIENT_ID=
TWITCH_CLIENT_SECRET=
TWITCH_CLIENT_REDIRECT_URI=${OAUTH2_REDIRECT_URI}/twitch
EOF
  echo -e "${WARNING} ${ANSI_BOLD}3rd-party OAuth2 authentication will ${ANSI_RED}${ANSI_UNDERLINE}NOT${ANSI_RESET}${ANSI_BOLD} work unless the related client IDs and secrets are manually set inside the root directory '${ANSI_UNDERLINE}.env${ANSI_RESET}${ANSI_BOLD}' file!"
  echo -e "${SUCCESS} ${ANSI_BOLD}Successfully completed setup for '${ANSI_UNDERLINE}root${ANSI_RESET}${ANSI_BOLD}'!"
}

function setup_gacha() {
  local module_name="gacha"
  echo -e "${INFO} ${ANSI_BOLD}Setting '${ANSI_UNDERLINE}${module_name}${ANSI_RESET}${ANSI_BOLD}'..."
  if [[ ! -d "./${module_name}" ]]; then
    echo -e "${ERROR} ${ANSI_BOLD}Directory '${ANSI_UNDERLINE}./${module_name}${ANSI_RESET}${ANSI_BOLD}' not found! Skipping...${ANSI_RESET}"
    return 1
  fi
cat <<EOF > ./${module_name}/.env
KAFKA_SERVER=${KAFKA_SERVER}

REDIS_HOST=${REDIS_HOST}
REDIS_PORT=${REDIS_PORT}

POSTGRES_HOST=${POSTGRES_HOST}
POSTGRES_PORT=${POSTGRES_PORT}
POSTGRES_USER=gacha_user
POSTGRES_PASSWORD=gacha_user
POSTGRES_DATABASE=gacha_db

MONGO_HOST=${MONGO_HOST}
MONGO_PORT=${MONGO_PORT}
MONGO_DATABASE=gacha_db
EOF
  echo -e "${SUCCESS} ${ANSI_BOLD}Successfully completed setup for '${ANSI_UNDERLINE}${module_name}${ANSI_RESET}${ANSI_BOLD}'!"
}

function setup_game() {
  local module_name="game"
  echo -e "${INFO} ${ANSI_BOLD}Setting '${ANSI_UNDERLINE}${module_name}${ANSI_RESET}${ANSI_BOLD}'..."
  if [[ ! -d "./${module_name}" ]]; then
    echo -e "${ERROR} ${ANSI_BOLD}Directory '${ANSI_UNDERLINE}./${module_name}${ANSI_RESET}${ANSI_BOLD}' not found! Skipping...${ANSI_RESET}"
    return 1
  fi
cat <<EOF > ./${module_name}/.env
WARP_CLIENT_URI=${GACHA_SERVICE_BASE_URI}/warp
BANNER_CLIENT_URI=${GACHA_SERVICE_BASE_URI}/banner
INVENTORY_CLIENT_URI=${INVENTORY_SERVICE_BASE_URI}/inventory
CHARACTER_CLIENT_URI=${INVENTORY_SERVICE_BASE_URI}/character
WEAPON_CLIENT_URI=${INVENTORY_SERVICE_BASE_URI}/weapon
SHOP_CLIENT_URI=${SHOP_SERVICE_BASE_URI}/shop

POSTGRES_HOST=${POSTGRES_HOST}
POSTGRES_PORT=${POSTGRES_PORT}
POSTGRES_USER=game_user
POSTGRES_PASSWORD=game_user
POSTGRES_DATABASE=game_db
EOF
  echo -e "${SUCCESS} ${ANSI_BOLD}Successfully completed setup for '${ANSI_UNDERLINE}${module_name}${ANSI_RESET}${ANSI_BOLD}'!"
}

function setup_gateway() {
  local module_name="gateway"
  echo -e "${INFO} ${ANSI_BOLD}Setting '${ANSI_UNDERLINE}${module_name}${ANSI_RESET}${ANSI_BOLD}'..."
  if [[ ! -d "./${module_name}" ]]; then
    echo -e "${ERROR} ${ANSI_BOLD}Directory '${ANSI_UNDERLINE}./${module_name}${ANSI_RESET}${ANSI_BOLD}' not found! Skipping...${ANSI_RESET}"
    return 1
  fi
cat <<EOF > ./${module_name}/.env
GAME_SERVICE_BASE_URI=${GAME_SERVICE_BASE_URI}
BANNER_SERVICE_BASE_URI=${GAME_SERVICE_BASE_URI}
CHARACTER_SERVICE_BASE_URI=${INVENTORY_SERVICE_BASE_URI}
WEAPON_SERVICE_BASE_URI=${INVENTORY_SERVICE_BASE_URI}
USER_SERVICE_BASE_URI=${USER_SERVICE_BASE_URI}
AUTH_SERVICE_BASE_URI=${USER_SERVICE_BASE_URI}

JWT_SECRET=${JWT_SECRET}
EOF
  echo -e "${SUCCESS} ${ANSI_BOLD}Successfully completed setup for '${ANSI_UNDERLINE}${module_name}${ANSI_RESET}${ANSI_BOLD}'!"
}

function setup_inventory() {
  local module_name="inventory"
  echo -e "${INFO} ${ANSI_BOLD}Setting '${ANSI_UNDERLINE}${module_name}${ANSI_RESET}${ANSI_BOLD}'..."
  if [[ ! -d "./${module_name}" ]]; then
    echo -e "${ERROR} ${ANSI_BOLD}Directory '${ANSI_UNDERLINE}./${module_name}${ANSI_RESET}${ANSI_BOLD}' not found! Skipping...${ANSI_RESET}"
    return 1
  fi
cat <<EOF > ./${module_name}/.env
KAFKA_SERVER=${KAFKA_SERVER}

REDIS_HOST=${REDIS_HOST}
REDIS_PORT=${REDIS_PORT}
REDIS_DATABASE=0

POSTGRES_HOST=${POSTGRES_HOST}
POSTGRES_PORT=${POSTGRES_PORT}
POSTGRES_USER=inventory_user
POSTGRES_PASSWORD=inventory_user
POSTGRES_DATABASE=inventory_db

MONGO_HOST=${MONGO_HOST}
MONGO_PORT=${MONGO_PORT}
MONGO_DATABASE=inventory_db
EOF
  echo -e "${SUCCESS} ${ANSI_BOLD}Successfully completed setup for '${ANSI_UNDERLINE}${module_name}${ANSI_RESET}${ANSI_BOLD}'!"
}

function setup_shop() {
  local module_name="shop"
  echo -e "${INFO} ${ANSI_BOLD}Setting '${ANSI_UNDERLINE}${module_name}${ANSI_RESET}${ANSI_BOLD}'..."
  if [[ ! -d "./${module_name}" ]]; then
    echo -e "${ERROR} ${ANSI_BOLD}Directory '${ANSI_UNDERLINE}./${module_name}${ANSI_RESET}${ANSI_BOLD}' not found! Skipping...${ANSI_RESET}"
    return 1
  fi
cat <<EOF > ./${module_name}/.env
REDIS_HOST=${REDIS_HOST}
REDIS_PORT=${REDIS_PORT}

MONGO_HOST=${MONGO_HOST}
MONGO_PORT=${MONGO_PORT}
MONGO_DATABASE=shop_db
EOF
  echo -e "${SUCCESS} ${ANSI_BOLD}Successfully completed setup for '${ANSI_UNDERLINE}${module_name}${ANSI_RESET}${ANSI_BOLD}'!"
}

function setup_user() {
  local module_name="user"
  echo -e "${INFO} ${ANSI_BOLD}Setting '${ANSI_UNDERLINE}${module_name}${ANSI_RESET}${ANSI_BOLD}'..."
  if [[ ! -d "./${module_name}" ]]; then
    echo -e "${ERROR} ${ANSI_BOLD}Directory '${ANSI_UNDERLINE}./${module_name}${ANSI_RESET}${ANSI_BOLD}' not found! Skipping...${ANSI_RESET}"
    return 1
  fi
cat <<EOF > ./${module_name}/.env
SUPABASE_CLIENT_URI=${SUPABASE_CLIENT_URI}
SUPABASE_EXTERNAL_CLIENT_URI=${SUPABASE_EXTERNAL_CLIENT_URI}

POSTGRES_HOST=${POSTGRES_HOST}
POSTGRES_PORT=${POSTGRES_PORT}
POSTGRES_USER=user_user
POSTGRES_PASSWORD=user_user
POSTGRES_DATABASE=user_db

KAFKA_SERVER=${KAFKA_SERVER}

JWT_SECRET=${JWT_SECRET}
EOF
  echo -e "${WARNING} ${ANSI_BOLD}3rd-party OAuth2 authentication will ${ANSI_RED}${ANSI_UNDERLINE}NOT${ANSI_RESET}${ANSI_BOLD} work unless the related client IDs and secrets are manually set inside the root directory '${ANSI_UNDERLINE}.env${ANSI_RESET}${ANSI_BOLD}' file!"
  echo -e "${SUCCESS} ${ANSI_BOLD}Successfully completed setup for '${ANSI_UNDERLINE}${module_name}${ANSI_RESET}${ANSI_BOLD}'!"
}

function setup_everything() {
  setup_root
  setup_gacha
  setup_game
  setup_gateway
  setup_inventory
  setup_shop
  setup_user
}

function delete_all() {
  local continue_choice=""
  # shellcheck disable=SC2034
  local continue_choices=('No' 'Yes')
  ask_question "Are you sure you want to delete all '${ANSI_UNDERLINE}.env${ANSI_RESET}${ANSI_BOLD}' files in the project?" continue_choices 0 continue_choice
  if [[ "$continue_choice" -eq 1 ]]; then
    find . -name ".env" -type f -delete
  fi
  clear_screen
}

function execute_setup() {
  local target_var=$1
  local choice="${!target_var}"

  case "$choice" in
    1) setup_everything ;;
    2) setup_root ;;
    3) setup_gacha ;;
    4) setup_game ;;
    5) setup_gateway ;;
    6) setup_inventory ;;
    7) setup_shop ;;
    8) setup_user ;;
    9) delete_all ;;
    *) echo -e "${ERROR} ${ANSI_BOLD}Unknown choice!" ;;
  esac
}

function main() {
  clear_screen
  print_header

  local env_choice=""
  local continue_choice=""
  # shellcheck disable=SC2034
  local operation_choices=('Exit' 'Everything' 'Root directory' 'Gacha' 'Game' 'Gateway' 'Inventory' 'Shop' 'User' 'Delete all')
  # shellcheck disable=SC2034
  local continue_choices=('No' 'Yes')

  while true; do
    ask_question "Please choose the '${ANSI_UNDERLINE}.env${ANSI_RESET}${ANSI_BOLD}' file you want to set up:" operation_choices 1 env_choice
    clear_screen
    if [[ "$env_choice" -eq 0 ]]; then
      exit_program
    fi

    execute_setup env_choice
    echo -e "${SUCCESS} ${ANSI_BOLD}All operations completed!"
    ask_question "Would you like to set up more files?" continue_choices 1 continue_choice
    clear_screen
    if [[ "$continue_choice" -eq 0 ]]; then
      exit_program
    fi
  done
}

main
