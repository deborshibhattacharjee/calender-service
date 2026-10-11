CREATE TABLE calenders(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    owner_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    timeZone VARCHAR(100) NOT NULL DEFAULT 'UTC',
    createdTime timestamptz NOT NULL DEFAULT now(),
    modifiedTime timestamptz NOT NULL DEFAULT now(),

    CONSTRAINT fk_calenders_owner FOREIGN KEY (owner_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE INDEX idx_calender_owner_id ON calenders(owner_id);