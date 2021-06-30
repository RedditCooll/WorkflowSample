const express = require("express");
const bodyParser = require('body-parser');
const mongoose = require('mongoose');

const mail = require('./routes/api/mail');
const data = require('./routes/api/data');

const app = express();
// Parse incoming requests data (https://github.com/expressjs/body-parser)
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));

// Port that the webserver listens to
const port = process.env.PORT || 5000;

app.listen(port, () => {
    console.log(`Server listening on port ${port}`);
});

// Database configuration
mongoose.connect('mongodb://localhost:27017/test', {useNewUrlParser: true, useUnifiedTopology: true});

// Routes
app.use("/api/mail", mail);
app.use("/api/data", data);

