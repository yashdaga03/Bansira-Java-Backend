const express = require('express');
const cors = require('cors');
const bodyParser = require('body-parser');
const emailRoutes = require('./src/routes/emailRoutes');

const app = express();
app.use(cors());
app.use(bodyParser.json());
app.use("/api/v1", emailRoutes);

module.exports = app;