const express = require('express')
const app = express()
const port = 3000
const config = {
    host: 'db',
    user: 'root',
    password: 'root',
    database: 'nodedb'
};
const mysql = require('mysql')
const connection = mysql.createConnection(config)

const sql = `INSERT INTO people(name) values('Fulano')`
connection.query(sql)
connection.end()

const pool = mysql.createPool({
    connectionLimit: 10,
    password: 'root',
    user: 'root',
    database: 'nodedb',
    host: 'db',
    port: 3306
});

SelectAllElements = () => {
    return new Promise((resolve, reject) => {
        pool.query('SELECT * FROM people ', (error, elements) => {
            if (error) {
                return reject(error);
            }
            return resolve(elements);
        });
    });
};

app.get('/', async (req, res, next) => {
    try {
        const resultElements = await SelectAllElements();
        const msg = '<h1>Full Cycle Rocks!</h1>' + resultElements.map(e => e.name);
        res.send(msg);
    } catch (e) {
        console.log(e);
        res.sendStatus(500);
    }
});

app.listen(port, () => {
    console.log('Running on port' + port)
})