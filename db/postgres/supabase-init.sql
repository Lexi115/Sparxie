CREATE OR REPLACE FUNCTION public.set_default_role()
    RETURNS trigger AS
$$
BEGIN
    NEW.raw_app_meta_data := coalesce(NEW.raw_app_meta_data, '{}'::jsonb) || '{
      "roles": [
        "MEMBER"
      ]
    }'::jsonb;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;

CREATE OR REPLACE TRIGGER on_user_created_set_role
    BEFORE INSERT
    ON auth.users
    FOR EACH ROW
EXECUTE PROCEDURE public.set_default_role();
