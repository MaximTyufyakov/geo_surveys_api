# geo_surveys_api

API for geocontrol surveys.

Uses the MVC patern.

## To do

- DB connecttion and migration.
- Login with token.
- All Flutter SQL -> API SQL.
- Sync with flutter local db.

## Before build
1.Set secret environment variables or CLI arguments (look application.properties).

## Docker
- For jar create: ./mvnw package; java -jar target/geo_surveys_api-0.0.1-SNAPSHOT.jar

## Database administration

### Test users

1. login = 'test_1', password = 'pas_1';
2. login = 'test_2', password = 'pas_2'.

### User create

- INSERT INTO public."userDto"(
  login, "password")
  VALUES ('login', crypt('password', gen_salt('bf')));

### Password update

- UPDATE public."userDto"
  SET password = crypt('password', gen_salt('bf'))
  WHERE login = 'login';

