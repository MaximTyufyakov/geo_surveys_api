# geo_surveys_api

API for geocontrol surveys.

Uses the MVC patern.

## To do

- DB connecttion and migration.
- Login with token.
- All Flutter SQL -> API SQL.
- Sync with flutter local db.

## Build
1. Create database:
   * By geosurveys_schema.sql (recommended);
   * By sping:
     1. spring.jpa.hibernate.ddl-auto=create;
     2. Run app;
     3. Create "updated at" triggers for all entities ([Trigger for updated_at](#trigger-for-updated_at)).
2. Set secret environment variables or CLI arguments (look application.properties).

## Database administration

### Trigger for updated_at
CREATE OR REPLACE FUNCTION set_updated_at()\
RETURNS TRIGGER AS \$$\
BEGIN\
NEW.updated_at := NOW();\
RETURN NEW;\
END;\
$$ LANGUAGE plpgsql;\

CREATE TRIGGER before_update_user\
BEFORE UPDATE ON user\
FOR EACH ROW\
EXECUTE FUNCTION set_updated_at();

### Test users

1. login = 'test_1', password = 'pas_1';
2. login = 'test_2', password = 'pas_2'.

### User create

- INSERT INTO public."user"(
  login, "password")
  VALUES ('login', crypt('password', gen_salt('bf')));

### Password update

- UPDATE public."user"
  SET password = crypt('password', gen_salt('bf'))
  WHERE login = 'login';

