DO $$
BEGIN
  IF NOT EXISTS (
    SELECT 1
    FROM information_schema.columns
    WHERE table_schema = 'plan_control'
      AND table_name = 'products'
      AND column_name = 'user_update_id'
  ) THEN
    ALTER TABLE plan_control.products ADD COLUMN "user_update_id" uuid NULL;
  END IF;
END$$;

DO $$
BEGIN
  -- Adiciona a coluna se não existir
  IF NOT EXISTS (
    SELECT 1 FROM information_schema.columns
    WHERE table_schema = 'plan_control'
      AND table_name = 'products'
      AND column_name = 'user_update_id'
  ) THEN
    ALTER TABLE plan_control.products
    ADD COLUMN user_update_id uuid;
  END IF;

  -- Adiciona a FK se não existir
  IF NOT EXISTS (
    SELECT 1 FROM pg_constraint
    WHERE conname = 'fk_products_user_update'
  ) THEN
    ALTER TABLE plan_control.products
    ADD CONSTRAINT fk_products_user_update
    FOREIGN KEY (user_update_id)
    REFERENCES auth.users (uuid);
  END IF;
END
$$;