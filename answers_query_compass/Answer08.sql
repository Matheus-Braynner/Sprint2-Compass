#AFTER CREATE A TABLE YOU CAN USE THIS COMMAND TO UPDATE YOUR TABLE WITH A FOREIGN KEY.
ALTER TABLE NOTAS_FISCAIS 
ADD CONSTRAINT ID_FK FOREIGN KEY(ID) REFERENCES PRODUTO (ID); 

/*
USE COMPASS;
CREATE TABLE PRODUTO(
       ID INT PRIMARY KEY,
        NOME VARCHAR(50) NOT NULL,
        DESCRICAO VARCHAR(100) NOT NULL,
        DESCONTO FLOAT NOT NULL,
        DATA DATE);
*/


/*
CREATE TABLE NOTAS_FISCAIS (
			NUMERO INT AUTO_INCREMENT PRIMARY KEY,
            ID INT,
            CONSTRAINT ID
            FOREIGN KEY (ID)
            REFERENCES PRODUTO(ID),
            VALOR FLOAT NOT NULL);
*/