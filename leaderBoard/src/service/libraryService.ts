import axios from 'axios';
import dotenv from 'dotenv';
import { sendDepartmentMail } from "./communicationService";

dotenv.config();

const BASE_URL = process.env.LIBRARYURL;

// can also be called through environment variables
const auth = {
  username: 'admin',
  password: 'password',
}

export const findWinnerDepartment = (departments: any[]) => {
  if (!departments.length) {
    throw new Error('No departments found');
  }

  const departmentWithMaxHits = departments.reduce((max, dept) => 
    dept.departmentHits > max.departmentHits ? dept : max, departments[0]);

  return departmentWithMaxHits;
};

export const getDepartments = async () => {
  const response = await axios.get(`${BASE_URL}/api/departments`, { auth });
  const departments = response.data;
  const departmentWithMaxHits = findWinnerDepartment(departments);
  sendDepartmentMail(departmentWithMaxHits);
  return departmentWithMaxHits;
};

export const getBooks = async () => {
  console.log('${BASE_URL}/api/books');
  const response = await axios.get(`${BASE_URL}/api/books`, { auth });
  const books = response.data.content;

  const weeklyHit = getBookWithMaxWeeklyHits(books);
  const monthlyHit = getBookWithMaxMonthlyHits(books);
  const dailyHit = getBookWithMaxDailyHits(books);

  return {
    weeklyHit,
    monthlyHit,
    dailyHit
  };
};

const getBookWithMaxWeeklyHits = (books: any[]) => {
  if (!books.length) {
    throw new Error('No books found');
  }

  return books.reduce((max, book) => 
    book.weeklyHit > max.weeklyHit ? book : max, books[0]);
};

const getBookWithMaxMonthlyHits = (books: any[]) => {
  if (!books.length) {
    throw new Error('No books found');
  }
  return books.reduce((max, book) => 
    book.monthlyHit > max.monthlyHit ? book : max, books[0]);
};

const getBookWithMaxDailyHits = (books: any[]) => {
  if (!books.length) {
    throw new Error('No books found');
  }

  return books.reduce((max, book) => 
    book.todayHit > max.todayHit ? book : max, books[0]);
};