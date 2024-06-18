const nodemailer = require('nodemailer');
const config = require('../../config');
const convertTemplate = require('../utils/templateConverter');

const transporter = nodemailer.createTransport({
  host: config.smtp.host,
  port: config.smtp.port,
  secure: false,
  auth: {
    user: config.smtp.user,
    pass: config.smtp.pass,
  },
});

const sendEmail = async (to, subject, templateName, templateData) => {
  const html = convertTemplate(templateName, templateData);

  const mailOptions = {
    from: config.smtp.user,
    to,
    subject,
    html,
  };

  return transporter.sendMail(mailOptions);
};

module.exports = {
  sendEmail,
};
