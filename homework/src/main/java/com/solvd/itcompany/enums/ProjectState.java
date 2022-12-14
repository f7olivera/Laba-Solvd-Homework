package com.solvd.itcompany.enums;

import java.time.Instant;

import static com.solvd.itcompany.company.Project.timestamp2date;

public enum ProjectState {
    NOT_STARTED {
        @Override
        public String toString() {
            return "Project not started.";
        }
    },
    STARTED(Instant.now().getEpochSecond()) {
        @Override
        public String toString() {
            return "Project started at " + timestamp2date(getTimestamp()) + " with priority " + getPriority() + ".";
        }
    },
    FINISHED(Instant.now().getEpochSecond(), -1) {
        @Override
        public String toString() {
            return "Project finished at " + timestamp2date(getTimestamp()) + ".";
        }
    };

    private long timestamp;
    private int priority = 0;

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
