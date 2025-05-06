CREATE TABLE sf_user (
    id SERIAL PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    email VARCHAR(200) UNIQUE NOT NULL,
    password_hash TEXT NOT NULL,
    locale VARCHAR(10) DEFAULT 'pt-BR',
    default_currency VARCHAR(3) DEFAULT 'BRL',
    timezone VARCHAR(70) DEFAULT 'America/Sao_Paulo',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_active BOOLEAN DEFAULT TRUE,
    role VARCHAR(20) DEFAULT 'user',
    plan VARCHAR(30) DEFAULT 'free',
    email_verified BOOLEAN DEFAULT FALSE,
    verification_token VARCHAR(300),
    avatar_url TEXT,
    last_login TIMESTAMP,
    two_factor_enabled BOOLEAN DEFAULT FALSE
);