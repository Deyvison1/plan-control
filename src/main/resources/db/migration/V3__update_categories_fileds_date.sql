DO $$
BEGIN
  IF EXISTS (
    SELECT 1
    FROM information_schema.columns
    WHERE table_name = 'categories' AND column_name = 'created_at'
  ) AND NOT EXISTS (
    SELECT 1
    FROM information_schema.columns
    WHERE table_name = 'categories' AND column_name = 'created'
  ) THEN
    ALTER TABLE plan_control.categories RENAME COLUMN created_at TO created;
  END IF;
END$$;

DO $$
BEGIN
  IF EXISTS (
    SELECT 1
    FROM information_schema.columns
    WHERE table_name = 'categories' AND column_name = 'updated_at'
  ) AND NOT EXISTS (
    SELECT 1
    FROM information_schema.columns
    WHERE table_name = 'categories' AND column_name = 'updated'
  ) THEN
    ALTER TABLE plan_control.categories RENAME COLUMN updated_at TO updated;
  END IF;
END$$;