.DEFAULT_GOAL := help

all-tests: ## run all tests
	./gradlew clean build
	./gradlew run &
	sleep 5
	./gradlew enqueuer
	./gradlew --stop &
	sleep 2
	clear
	echo "******** OK, ALL TESTS ARE GREEN **************"