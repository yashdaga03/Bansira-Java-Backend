import axios from 'axios';
import dotenv from 'dotenv';

dotenv.config();

const BASE_URL = process.env.COMMUNICATIONURL;

export const sendDepartmentMail = async (data: any) => {
    try {
        const response = await axios.post(BASE_URL + "api/v1/send-email", {
            to: "dummy@mailinator.com",
            subject: "Updates of the winner of the Month!",
            templateName: "winner",
            templateData: {
                "winnerDepartment": data.name,
                "hits": data.departmentHits
            }
        });
    } catch(error) {
        throw new Error(`Failed to send Email`);
    }
  };