# Tekki

Tekki is a guessing game developed using Java Spring Framework, Maven, HTML, CSS, Thymeleaf, and JUnit for testing by a group of MIAGE student from Université Paris-Cité: Max, Younes, Elouan, Laskhan and Hai-My. The aim of the game is to identify a person based on hints provided by the computer. Players start with 150 points and can ask questions to narrow down the possibilities or make a direct guess.

## Table of Contents

- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Rules](#rules)
- [Technologies](#technologies)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Features

- Interactive guessing game
- Points-based system
- Dynamic question and answer interaction
- Direct guessing option
- Points deduction for each question asked
- User-friendly interface with HTML, CSS, and Thymeleaf
- Comprehensive unit tests with JUnit

## Installation

To install and run Tekki locally, follow these steps:

1. **Clone the repository:**
    ```sh
    git clone https://github.com/jolehuit/tekki.git
    cd tekki
    ```

2. **Build the project using Maven:**
    ```sh
    mvn clean install
    ```

3. **Run the application:**
    ```sh
    mvn spring-boot:run
    ```

## Usage

1. Start the application.
2. Open your web browser and navigate to `http://localhost:8080`.
3. At the beginning of the game, you will have 150 points.
4. Select questions provided by the computer to ask about the person.
5. Each question asked will deduct 10 points from your total points.
6. You can choose to guess directly without asking questions.
7. If guessing directly, you will be presented with a list of people to choose from.
8. Aim to guess the correct person with the highest points remaining.

## Rules

1. Players start with 150 points.
2. Each question asked deducts 10 points from the total points.
3. Players can ask unlimited questions or guess directly.
4. A correct guess wins the game, and the remaining points are the final score.

## Technologies

- Java
- Spring Framework
- Maven
- HTML
- CSS
- Thymeleaf
- JUnit

## Testing

Tekki includes unit tests to ensure the functionality and reliability of the game. The tests are written using JUnit.

To run the tests, use the following Maven command:
```sh
mvn test
```

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository.
2. Create your feature branch (`git checkout -b feature/YourFeature`).
3. Commit your changes (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature/YourFeature`).
5. Open a Pull Request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

For any questions or suggestions, please contact:

- **Max Penso**
- **GitHub:** [jolehuit](https://github.com/jolehuit)

- **Hai-My Le**
- **GitHub:** [haimyle](https://github.com/haimyle)
