DROP TABLE IF EXISTS PRODUCT;
 
CREATE TABLE PRODUCT (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  nom VARCHAR(250) NOT NULL,
  prix INT NOT NULL
);
