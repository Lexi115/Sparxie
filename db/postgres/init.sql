-- @formatter:off


--
-- User service database
--
CREATE USER user_user WITH PASSWORD 'user_user';
CREATE DATABASE user_db OWNER user_user;


--
-- Game orchestrator database
--
CREATE USER game_user WITH PASSWORD 'game_user';
CREATE DATABASE game_db OWNER game_user;


--
-- Inventory service database
--
CREATE USER inventory_user WITH PASSWORD 'inventory_user';
CREATE DATABASE inventory_db OWNER inventory_user;


--
-- Gacha service database
--
CREATE USER gacha_user WITH PASSWORD 'gacha_user';
CREATE DATABASE gacha_db OWNER gacha_user;


--
-- Supabase database
--
CREATE DATABASE supabase_db;

\c supabase_db

CREATE SCHEMA auth;
CREATE TYPE auth.factor_type AS ENUM ('totp', 'webauthn');
CREATE TYPE auth.factor_status AS ENUM ('unverified', 'verified');
CREATE TYPE auth.aal_level AS ENUM ('aal1', 'aal2', 'aal3');

-- @formatter:on
