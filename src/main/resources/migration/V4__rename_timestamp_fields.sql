-- Users table
ALTER TABLE users
    RENAME COLUMN createdtime TO created_time;

ALTER TABLE users
    RENAME COLUMN modifiedtime TO modified_time;

-- Events table
ALTER TABLE events
    RENAME COLUMN createdtime TO created_time;

ALTER TABLE events
    RENAME COLUMN modifiedtime TO modified_time;