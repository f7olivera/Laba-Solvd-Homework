package com.solvd.itcompany.enums;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

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
            return "Project started at " + formatDate() + " with priority " + getPriority() + ".";
        }
    },
    FINISHED(Instant.now().getEpochSecond(), -1) {
        @Override
        public String toString() {
            return "Project finished at " + formatDate() + ".";
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

    public String formatDate() {
        Date date = new Date(timestamp * 1000L);
        SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        return jdf.format(date) + " (UTC)";
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
