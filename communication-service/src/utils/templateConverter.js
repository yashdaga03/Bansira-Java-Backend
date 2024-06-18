const ejs = require('ejs');
const fs = require('fs');
const path = require('path');

const convertTemplate = (templateName, data) => {
  const templatePath = path.join(__dirname, '../templates', `${templateName}.ejs`);
  const templateContent = fs.readFileSync(templatePath, 'utf-8');
  return ejs.render(templateContent, data);
};

module.exports = convertTemplate;
