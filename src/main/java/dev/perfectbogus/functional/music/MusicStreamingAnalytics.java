package dev.perfectbogus.functional.music;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MusicStreamingAnalytics {

    // 1. Total play count per genre
    public static Map<String, Long> totalPlayCountByGenre(List<Track> tracks) {
        if (tracks == null) throw new IllegalArgumentException("Tracks cannot be null");
        // TODO: implement
        return tracks.stream()
                .collect(Collectors.groupingBy(
                        Track::genre,
                        Collectors.summingLong(
                                Track::playCount
                        )
                ));
    }

    // 2. Partition tracks by explicit content
    // true  → explicit track titles sorted alphabetically
    // false → clean track titles sorted alphabetically
    public static Map<Boolean, List<String>> partitionByExplicit(List<Track> tracks) {
        if (tracks == null) throw new IllegalArgumentException("Tracks cannot be null");
        // TODO: implement
        return tracks.stream()
                .collect(Collectors.partitioningBy(
                        Track::explicit,
                        Collectors.mapping(
                                Track::title,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list -> list.stream().sorted().toList()
                                )
                        )
                ));
    }

    // 3. Longest track title per genre
    // Returns the title of the track with the highest durationSeconds per genre
    // Returns "N/A" if no track found
    public static Map<String, String> longestTrackTitleByGenre(List<Track> tracks) {
        if (tracks == null) throw new IllegalArgumentException("Tracks cannot be null");
        // TODO: implement
        return tracks.stream()
                .collect(Collectors.groupingBy(
                        Track::genre,
                        Collectors.collectingAndThen(
                                Collectors.maxBy(Comparator.comparingInt(Track::durationSeconds)),
                                opt -> opt.map(Track::title).orElse("N/A")
                        )
                ));
    }

    // 4. Artists with ACTIVE tracks in more than one genre
    // Returns an unmodifiable List<String> sorted alphabetically
    private static final Predicate<Track> TRACK_ACTIVE = (t -> t.status() == TrackStatus.ACTIVE);
    private static final Predicate<Map.Entry<String, Set<String>>> MORE_THAN_ONE = (e -> e.getValue().size() > 1);

    public static List<String> artistsWithMultipleGenres(List<Track> tracks) {
        if (tracks == null) throw new IllegalArgumentException("Tracks cannot be null");
        // TODO: implement
        return tracks.stream()
                .filter(TRACK_ACTIVE)
                .collect(Collectors.groupingBy(
                        Track::artist,
                        Collectors.mapping(
                                Track::genre,
                                Collectors.toSet()
                        )
                )).entrySet().stream()
                .filter(MORE_THAN_ONE)
                .map(Map.Entry::getKey)
                .sorted()
                .toList();
    }

    // 5. Genres where all tracks are ACTIVE
    // Returns List<String> sorted alphabetically
    public static List<String> genresWithAllTracksActive(List<Track> tracks) {
        if (tracks == null) throw new IllegalArgumentException("Tracks cannot be null");
        // TODO: implement
        return tracks.stream()
                .collect(Collectors.groupingBy(
                        Track::genre,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream().allMatch(TRACK_ACTIVE)
                        )
                )).entrySet().stream()
                .filter(Map.Entry::getValue)
                .map(Map.Entry::getKey)
                .sorted()
                .toList();
    }

    // 6. All unique moods per genre as an unmodifiable Set
    // Flatten moods from all tracks in each genre — set must be unmodifiable
    private static final Function<Track, Stream<String>> moodsStream = (t -> t.moods().stream());
    public static Map<String, Set<String>> moodsByGenre(List<Track> tracks) {
        if (tracks == null) throw new IllegalArgumentException("Tracks cannot be null");
        // TODO: implement
        return tracks.stream()
                .collect(Collectors.groupingBy(
                        Track::genre,
                        Collectors.flatMapping(
                                moodsStream,
                                Collectors.collectingAndThen(
                                        Collectors.toSet(),
                                        Collections::unmodifiableSet
                                )
                        )
                ));
    }

    // 7. Genre play count summary using Collectors.teeing
    // Returns Map<genre, "X tracks, Y total plays"> — computed in a single pass per genre
    public static Map<String, String> genrePlayCountSummary(List<Track> tracks) {
        if (tracks == null) throw new IllegalArgumentException("Tracks cannot be null");
        // TODO: implement using Collectors.teeing
        return tracks.stream()
                .collect(Collectors.groupingBy(
                        Track::genre,
                        Collectors.teeing(
                                Collectors.counting(),
                                Collectors.summingLong(Track::playCount),
                                (c, s) -> c + " tracks, " + s + " total plays"
                        )
                ));
    }

    // 8. Multi-level grouping: genre → artist → track titles
    // Only ACTIVE tracks — titles sorted alphabetically at every level
    public static Map<String, Map<String, List<String>>> tracksByGenreAndArtist(List<Track> tracks) {
        if (tracks == null) throw new IllegalArgumentException("Tracks cannot be null");
        // TODO: implement using nested groupingBy
        return tracks.stream()
                .filter(TRACK_ACTIVE)
                .collect(Collectors.groupingBy(
                        Track::genre,
                        Collectors.groupingBy(
                                Track::artist,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        list -> list.stream().map(Track::title).sorted().toList()
                                )
                        )
                ));
    }
}
