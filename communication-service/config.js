const dotenv = require('dotenv');

dotenv.config();

module.exports = {
  port: process.env.PORT,
  smtp: {
    host: process.env.SMTP_HOST,
    port: process.env.SMTP_PORT,
    user: process.env.SMTP_USER,
    pass: process.env.SMTP_PASS,
  },
};
