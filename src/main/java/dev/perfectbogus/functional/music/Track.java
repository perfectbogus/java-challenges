package dev.perfectbogus.functional.music;

import java.util.List;

public record Track(
        String trackId,
        String title,
        String artist,
        String album,
        String genre,
        int durationSeconds,
        long playCount,
        int releaseYear,
        boolean explicit,
        TrackStatus status,
        List<String> moods
) {}
