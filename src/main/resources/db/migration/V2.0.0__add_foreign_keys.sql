ALTER TABLE expense_claims
    ADD CONSTRAINT fk_expense_claims_users FOREIGN KEY (payer_id) REFERENCES users (id);

ALTER TABLE expense_claims
    ADD CONSTRAINT fk_expense_claims_categories FOREIGN KEY (category_id) REFERENCES categories (id);
