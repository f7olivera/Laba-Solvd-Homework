package com.solvd.itcompany.company;

import java.time.Instant;

enum ProjectState {
    NOT_STARTED,
    STARTED(Instant.now().getEpochSecond()) {
        @Override
        public String toString() {
            return "Project started at " + getTimestamp() + "with priority " + getPriority() + ".";
        }
    },
    FINISHED(Instant.now().getEpochSecond(), -1) {
        @Override
        public String toString() {
            return "Project finished at " + getTimestamp();
        }
    };

    private long timestamp;
    private int priority;

    ProjectState() {
    }

    ProjectState(long timestamp) {
        this.timestamp = timestamp;
    }

    ProjectState(long timestamp, int priority) {
        this(timestamp);
        this.priority = priority;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
