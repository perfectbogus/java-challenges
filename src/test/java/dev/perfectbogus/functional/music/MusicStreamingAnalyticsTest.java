package dev.perfectbogus.functional.music;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MusicStreamingAnalyticsTest {

    private List<Track> tracks;

    @BeforeEach
    void setUp() {
        tracks = List.of(
                new Track("T001", "Lose Yourself",       "Eminem",        "8 Mile",          "Hip-Hop", 326, 9500000L, 2002, true,  TrackStatus.ACTIVE,      List.of("intense", "motivational")),
                new Track("T002", "Rap God",             "Eminem",        "MMLP2",           "Hip-Hop", 363, 8200000L, 2013, true,  TrackStatus.ACTIVE,      List.of("intense", "energetic")),
                new Track("T003", "Without Me",          "Eminem",        "The Eminem Show",  "Hip-Hop", 290, 7100000L, 2002, true,  TrackStatus.RESTRICTED,  List.of("energetic", "fun")),
                new Track("T004", "Bohemian Rhapsody",   "Queen",         "A Night at Opera", "Rock",    354, 8800000L, 1975, false, TrackStatus.ACTIVE,      List.of("epic", "emotional")),
                new Track("T005", "Don't Stop Me Now",   "Queen",         "Jazz",            "Rock",    210, 7400000L, 1978, false, TrackStatus.ACTIVE,      List.of("energetic", "fun", "happy")),
                new Track("T006", "Under Pressure",      "Queen",         "Hot Space",       "Rock",    248, 5500000L, 1982, false, TrackStatus.REMOVED,     List.of("intense", "emotional")),
                new Track("T007", "Blinding Lights",     "The Weeknd",    "After Hours",     "Pop",     200, 9900000L, 2019, false, TrackStatus.ACTIVE,      List.of("energetic", "romantic")),
                new Track("T008", "Starboy",             "The Weeknd",    "Starboy",         "Pop",     230, 8600000L, 2016, true,  TrackStatus.ACTIVE,      List.of("chill", "dark")),
                new Track("T009", "Save Your Tears",     "The Weeknd",    "After Hours",     "Pop",     215, 7700000L, 2020, false, TrackStatus.ACTIVE,      List.of("sad", "romantic")),
                new Track("T010", "Sicko Mode",          "Travis Scott",  "Astroworld",      "Hip-Hop", 312, 8100000L, 2018, true,  TrackStatus.ACTIVE,      List.of("intense", "dark", "energetic")),
                new Track("T011", "Antidote",            "Travis Scott",  "Rodeo",           "Hip-Hop", 269, 6300000L, 2015, true,  TrackStatus.ACTIVE,      List.of("chill", "dark")),
                new Track("T012", "Bad Guy",             "Billie Eilish", "When We Fall",    "Pop",     194, 9200000L, 2019, false, TrackStatus.ACTIVE,      List.of("dark", "chill")),
                new Track("T013", "Happier Than Ever",   "Billie Eilish", "Happier Than",    "Pop",     295, 6800000L, 2021, false, TrackStatus.ACTIVE,      List.of("emotional", "sad")),
                new Track("T014", "Therefore I Am",      "Billie Eilish", "Happier Than",    "Pop",     174, 7300000L, 2020, false, TrackStatus.RESTRICTED,  List.of("dark", "intense")),
                new Track("T015", "We Will Rock You",    "Queen",         "News of World",   "Rock",    122, 9100000L, 1977, false, TrackStatus.ACTIVE,      List.of("energetic", "epic", "motivational"))
        );
    }

    // -------------------------------------------------------------------------
    // Task 1 — Total play count per genre
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 1 - totalPlayCountByGenre")
    class Task1 {

        @Test
        @DisplayName("Should return correct total play count per genre")
        void shouldReturnCorrectTotals() {
            Map<String, Long> result = MusicStreamingAnalytics.totalPlayCountByGenre(tracks);

            // Hip-Hop: T001+T002+T003+T010+T011 = 9500000+8200000+7100000+8100000+6300000 = 39200000
            assertEquals(39200000L, result.get("Hip-Hop"));

            // Rock: T004+T005+T006+T015 = 8800000+7400000+5500000+9100000 = 30800000
            assertEquals(30800000L, result.get("Rock"));

            // Pop: T007+T008+T009+T012+T013+T014 = 9900000+8600000+7700000+9200000+6800000+7300000 = 49500000
            assertEquals(49500000L, result.get("Pop"));
        }

        @Test
        @DisplayName("Should contain all genres")
        void shouldContainAllGenres() {
            Map<String, Long> result = MusicStreamingAnalytics.totalPlayCountByGenre(tracks);
            assertTrue(result.containsKey("Hip-Hop"));
            assertTrue(result.containsKey("Rock"));
            assertTrue(result.containsKey("Pop"));
        }

        @Test
        @DisplayName("Should return empty map for empty list")
        void shouldReturnEmptyForEmptyList() {
            assertTrue(MusicStreamingAnalytics.totalPlayCountByGenre(List.of()).isEmpty());
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> MusicStreamingAnalytics.totalPlayCountByGenre(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 2 — Partition tracks by explicit content
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 2 - partitionByExplicit")
    class Task2 {

        @Test
        @DisplayName("Should correctly partition into explicit and clean tracks")
        void shouldPartitionCorrectly() {
            Map<Boolean, List<String>> result = MusicStreamingAnalytics.partitionByExplicit(tracks);

            // Explicit (true): T001,T002,T003,T008,T010,T011
            assertTrue(result.get(true).containsAll(
                    List.of("Lose Yourself", "Rap God", "Without Me", "Starboy", "Sicko Mode", "Antidote")));
            assertEquals(6, result.get(true).size());

            // Clean (false): T004,T005,T006,T007,T009,T012,T013,T014,T015
            assertTrue(result.get(false).containsAll(
                    List.of("Bohemian Rhapsody", "Don't Stop Me Now", "Under Pressure",
                            "Blinding Lights", "Save Your Tears", "Bad Guy",
                            "Happier Than Ever", "Therefore I Am", "We Will Rock You")));
            assertEquals(9, result.get(false).size());
        }

        @Test
        @DisplayName("Should return titles sorted alphabetically in each partition")
        void shouldBeSortedAlphabetically() {
            Map<Boolean, List<String>> result = MusicStreamingAnalytics.partitionByExplicit(tracks);

            List<String> explicit = result.get(true);
            assertEquals(explicit.stream().sorted().toList(), explicit);

            List<String> clean = result.get(false);
            assertEquals(clean.stream().sorted().toList(), clean);
        }

        @Test
        @DisplayName("Should always contain both true and false keys")
        void shouldAlwaysHaveBothKeys() {
            Map<Boolean, List<String>> result = MusicStreamingAnalytics.partitionByExplicit(tracks);
            assertTrue(result.containsKey(true));
            assertTrue(result.containsKey(false));
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> MusicStreamingAnalytics.partitionByExplicit(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 3 — Longest track title per genre
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 3 - longestTrackTitleByGenre")
    class Task3 {

        @Test
        @DisplayName("Should return the title of the longest track per genre")
        void shouldReturnLongestTrackPerGenre() {
            Map<String, String> result = MusicStreamingAnalytics.longestTrackTitleByGenre(tracks);

            // Hip-Hop: T002 Rap God (363s)
            assertEquals("Rap God", result.get("Hip-Hop"));

            // Rock: T004 Bohemian Rhapsody (354s)
            assertEquals("Bohemian Rhapsody", result.get("Rock"));

            // Pop: T013 Happier Than Ever (295s)
            assertEquals("Happier Than Ever", result.get("Pop"));
        }

        @Test
        @DisplayName("Should return String not Optional")
        void shouldReturnString() {
            Map<String, String> result = MusicStreamingAnalytics.longestTrackTitleByGenre(tracks);
            result.values().forEach(v -> assertInstanceOf(String.class, v));
        }

        @Test
        @DisplayName("Should return empty map for empty list")
        void shouldReturnEmptyForEmptyList() {
            assertTrue(MusicStreamingAnalytics.longestTrackTitleByGenre(List.of()).isEmpty());
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> MusicStreamingAnalytics.longestTrackTitleByGenre(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 4 — Artists with ACTIVE tracks in more than one genre
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 4 - artistsWithMultipleGenres")
    class Task4 {

        @Test
        @DisplayName("Should return only artists with ACTIVE tracks in more than one genre")
        void shouldReturnMultiGenreArtists() {
            List<String> result = MusicStreamingAnalytics.artistsWithMultipleGenres(tracks);

            // All artists only appear in one genre in the test data → empty
            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Should detect cross-genre artist when added")
        void shouldDetectCrossGenreArtist() {
            // Add Eminem with a Pop ACTIVE track → now spans Hip-Hop + Pop
            var custom = new java.util.ArrayList<>(tracks);
            custom.add(new Track("T099", "Pop Track", "Eminem", "Pop Album",
                    "Pop", 200, 1000L, 2023, false, TrackStatus.ACTIVE, List.of("fun")));

            List<String> result = MusicStreamingAnalytics.artistsWithMultipleGenres(custom);
            assertTrue(result.contains("Eminem"));
        }

        @Test
        @DisplayName("Should not include artists whose cross-genre tracks are not ACTIVE")
        void shouldExcludeNonActiveCrossGenre() {
            // T003 (Without Me) is RESTRICTED — Eminem only has ACTIVE tracks in Hip-Hop
            List<String> result = MusicStreamingAnalytics.artistsWithMultipleGenres(tracks);
            assertFalse(result.contains("Eminem"));
        }

        @Test
        @DisplayName("Should return unmodifiable list")
        void shouldReturnUnmodifiableList() {
            List<String> result = MusicStreamingAnalytics.artistsWithMultipleGenres(tracks);
            assertThrows(UnsupportedOperationException.class, () -> result.add("test"));
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> MusicStreamingAnalytics.artistsWithMultipleGenres(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 5 — Genres where all tracks are ACTIVE
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 5 - genresWithAllTracksActive")
    class Task5 {

        @Test
        @DisplayName("Should return only genres where every track is ACTIVE")
        void shouldReturnFullyActiveGenres() {
            List<String> result = MusicStreamingAnalytics.genresWithAllTracksActive(tracks);

            // Hip-Hop: T003 is RESTRICTED → excluded
            // Rock: T006 is REMOVED → excluded
            // Pop: T014 is RESTRICTED → excluded
            assertTrue(result.isEmpty());
        }

        @Test
        @DisplayName("Should detect fully active genre when all tracks are active")
        void shouldDetectFullyActiveGenre() {
            List<Track> custom = List.of(
                    new Track("X001", "Track A", "Artist A", "Album", "Jazz", 200, 100L, 2020, false, TrackStatus.ACTIVE, List.of()),
                    new Track("X002", "Track B", "Artist B", "Album", "Jazz", 180, 200L, 2021, false, TrackStatus.ACTIVE, List.of())
            );
            List<String> result = MusicStreamingAnalytics.genresWithAllTracksActive(custom);
            assertEquals(List.of("Jazz"), result);
        }

        @Test
        @DisplayName("Should return results sorted alphabetically")
        void shouldBeSortedAlphabetically() {
            List<String> result = MusicStreamingAnalytics.genresWithAllTracksActive(tracks);
            assertEquals(result.stream().sorted().toList(), result);
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> MusicStreamingAnalytics.genresWithAllTracksActive(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 6 — All unique moods per genre as an unmodifiable Set
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 6 - moodsByGenre")
    class Task6 {

        @Test
        @DisplayName("Should collect all distinct moods flattened per genre")
        void shouldCollectAllDistinctMoodsPerGenre() {
            Map<String, Set<String>> result = MusicStreamingAnalytics.moodsByGenre(tracks);

            // Hip-Hop: T001(intense,motivational) T002(intense,energetic) T003(energetic,fun) T010(intense,dark,energetic) T011(chill,dark)
            assertEquals(Set.of("intense", "motivational", "energetic", "fun", "dark", "chill"), result.get("Hip-Hop"));

            // Rock: T004(epic,emotional) T005(energetic,fun,happy) T006(intense,emotional) T015(energetic,epic,motivational)
            assertEquals(Set.of("epic", "emotional", "energetic", "fun", "happy", "intense", "motivational"), result.get("Rock"));

            // Pop: T007(energetic,romantic) T008(chill,dark) T009(sad,romantic) T012(dark,chill) T013(emotional,sad) T014(dark,intense)
            assertEquals(Set.of("energetic", "romantic", "chill", "dark", "sad", "emotional", "intense"), result.get("Pop"));
        }

        @Test
        @DisplayName("Should return unmodifiable sets")
        void shouldReturnUnmodifiableSets() {
            Map<String, Set<String>> result = MusicStreamingAnalytics.moodsByGenre(tracks);
            assertThrows(UnsupportedOperationException.class,
                    () -> result.get("Pop").add("new-mood"));
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> MusicStreamingAnalytics.moodsByGenre(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 7 — Genre play count summary using teeing
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 7 - genrePlayCountSummary")
    class Task7 {

        @Test
        @DisplayName("Should return correctly formatted summary per genre")
        void shouldReturnFormattedSummary() {
            Map<String, String> result = MusicStreamingAnalytics.genrePlayCountSummary(tracks);

            // Hip-Hop: 5 tracks, 39200000 total plays
            assertEquals("5 tracks, 39200000 total plays", result.get("Hip-Hop"));

            // Rock: 4 tracks, 30800000 total plays
            assertEquals("4 tracks, 30800000 total plays", result.get("Rock"));

            // Pop: 6 tracks, 49500000 total plays
            assertEquals("6 tracks, 49500000 total plays", result.get("Pop"));
        }

        @Test
        @DisplayName("Should contain all genres")
        void shouldContainAllGenres() {
            Map<String, String> result = MusicStreamingAnalytics.genrePlayCountSummary(tracks);
            assertTrue(result.containsKey("Hip-Hop"));
            assertTrue(result.containsKey("Rock"));
            assertTrue(result.containsKey("Pop"));
        }

        @Test
        @DisplayName("Should return empty map for empty list")
        void shouldReturnEmptyForEmptyList() {
            assertTrue(MusicStreamingAnalytics.genrePlayCountSummary(List.of()).isEmpty());
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> MusicStreamingAnalytics.genrePlayCountSummary(null));
        }
    }

    // -------------------------------------------------------------------------
    // Task 8 — Multi-level grouping: genre → artist → track titles
    // -------------------------------------------------------------------------
    @Nested
    @DisplayName("Task 8 - tracksByGenreAndArtist")
    class Task8 {

        @Test
        @DisplayName("Should only include ACTIVE tracks")
        void shouldOnlyIncludeActiveTracks() {
            Map<String, Map<String, List<String>>> result =
                    MusicStreamingAnalytics.tracksByGenreAndArtist(tracks);

            // T003 (Without Me - Eminem) is RESTRICTED → not in result
            assertFalse(result.getOrDefault("Hip-Hop", Map.of())
                    .getOrDefault("Eminem", List.of())
                    .contains("Without Me"));

            // T006 (Under Pressure - Queen) is REMOVED → not in result
            assertFalse(result.getOrDefault("Rock", Map.of())
                    .getOrDefault("Queen", List.of())
                    .contains("Under Pressure"));

            // T014 (Therefore I Am - Billie Eilish) is RESTRICTED → not in result
            assertFalse(result.getOrDefault("Pop", Map.of())
                    .getOrDefault("Billie Eilish", List.of())
                    .contains("Therefore I Am"));
        }

        @Test
        @DisplayName("Should correctly group tracks by genre then artist")
        void shouldGroupCorrectly() {
            Map<String, Map<String, List<String>>> result =
                    MusicStreamingAnalytics.tracksByGenreAndArtist(tracks);

            // Hip-Hop / Eminem: T001 Lose Yourself, T002 Rap God (T003 excluded - RESTRICTED)
            assertEquals(List.of("Lose Yourself", "Rap God"),
                    result.get("Hip-Hop").get("Eminem"));

            // Hip-Hop / Travis Scott: T010 Sicko Mode, T011 Antidote
            assertEquals(List.of("Antidote", "Sicko Mode"),
                    result.get("Hip-Hop").get("Travis Scott"));

            // Rock / Queen: T004 Bohemian Rhapsody, T005 Don't Stop Me Now, T015 We Will Rock You (T006 excluded)
            assertEquals(List.of("Bohemian Rhapsody", "Don't Stop Me Now", "We Will Rock You"),
                    result.get("Rock").get("Queen"));

            // Pop / The Weeknd: T007 Blinding Lights, T008 Starboy, T009 Save Your Tears
            assertEquals(List.of("Blinding Lights", "Save Your Tears", "Starboy"),
                    result.get("Pop").get("The Weeknd"));

            // Pop / Billie Eilish: T012 Bad Guy, T013 Happier Than Ever (T014 excluded)
            assertEquals(List.of("Bad Guy", "Happier Than Ever"),
                    result.get("Pop").get("Billie Eilish"));
        }

        @Test
        @DisplayName("Should return titles sorted alphabetically per artist")
        void shouldHaveSortedTitles() {
            Map<String, Map<String, List<String>>> result =
                    MusicStreamingAnalytics.tracksByGenreAndArtist(tracks);

            result.values().forEach(artistMap ->
                    artistMap.values().forEach(titles ->
                            assertEquals(titles.stream().sorted().toList(), titles)
                    )
            );
        }

        @Test
        @DisplayName("Should throw for null input")
        void shouldThrowForNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> MusicStreamingAnalytics.tracksByGenreAndArtist(null));
        }
    }
}
