ALTER TABLE expense_claims
  ADD CONSTRAINT fk_expense_claims_famex_users FOREIGN KEY (payer_id) REFERENCES famex_users (id);
