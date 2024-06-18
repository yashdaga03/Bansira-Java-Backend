const emailService = require('../services/emailService');

const sendEmail = async (req, res) => {
  const { to, subject, templateName, templateData } = req.body;
  try {
    await emailService.sendEmail(to, subject, templateName, templateData);
    res.status(200).json({ message: 'Email sent successfully' });
  } catch (error) {
    res.status(500).json({ message: 'Failed to send email', error: error.message });
  }
};

module.exports = {
  sendEmail,
};
