ALTER TABLE t_orders ADD COLUMN order_name VARCHAR(255) DEFAULT NULL;
ALTER TABLE t_orders DROP COLUMN order_number;