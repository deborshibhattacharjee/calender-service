CREATE TABLE events(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),

    calender_id UUID NOT NULL,
    created_by UUID NOT NULL,

    title varchar(255) NOT NULL,
    description TEXT,
    location varchar(500),

    start_Time timestamptz NOT NULL,
    end_Time timestamptz NOT NULL,
    timeZone varchar(100) NOT NULL DEFAULT 'UTC',

    is_all_day BOOLEAN NOT NULL DEFAULT FALSE,
    createdTime timestamptz NOT NULL DEFAULT now(),
    modifiedTime timestamptz NOT NULL DEFAULT now(),

    CONSTRAINT fk_events_calender FOREIGN KEY(calender_id) REFERENCES calenders(id) ON DELETE CASCADE,
    CONSTRAINT fk_events_creator FOREIGN KEY(created_by) REFERENCES users(id),
    CONSTRAINT chk_events_time CHECK (end_Time>start_Time)
);

CREATE INDEX idx_events_calender_start ON events(calender_id, start_Time);