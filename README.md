# team-project-backend
Backend of application for a course 'Team project' that helps ukrainian refugees to find a help in poland.
## Clone project
to clone this project you should write command `git clone git@github.com:Nexxtgaming/team-project-backend.git` in terminal

## Requirements
* WSL 2 if you are on Windows
* Docker Desktop (https://docs.docker.com/desktop/windows/install/)

## Docker
1. **First run**
- To build a project use `mvn clean package -DskipTests`
- When you use docker first time or you changed Dockerfile use `docker build .` (with a dot at the end)
- To run a project use `docker-compose up`
- To shut down a project use `docker-compose down` if you want to have existing data in database or `docker-compose down -v` if you want to delete
all data from a database
2. **Run after some changes**
- `mvn clean package -DskipTests`
- `docker rmi team-project-backend:latest`
- `docker-compose up`

if you dont want to use docker to run a project change `spring.datasource.url` in application.properties
to `jdbc:postgresql://localhost:5432/testdb` **_remember to revert it before commit_**

## workflow
1. For each feature that you work create new branch.
2. Branch name should start from trello card's number eg. `3-login-and-registration-system`
3. When you finish and create Pull Request you have to wait for a review
4. If everything will be ok then a reviewer will merge your Pull Request
## Documentation
to check the documentation please run a project and go to http://localhost:8083/docs/swagger-ui/index.html
