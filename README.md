# ☕ Java Challenges

## 🧪 Running Tests

```bash
# Run all tests
./gradlew test

# Run tests for a specific challenge
./gradlew test --tests "challenges.arrays.TwoSumTest"

# Run a specific test method
./gradlew test --tests "challenges.arrays.TwoSumTest.testBasicCase"
```

## ▶️ Running a Challenge

```bash
./gradlew runChallenge -PmainClass=challenges.arrays.TwoSum
```