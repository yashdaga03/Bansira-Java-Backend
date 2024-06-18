import { Request, Response } from 'express';
import { getDepartments, getBooks } from '../service/libraryService';

export const fetchDepartments = async (req: Request, res: Response) => {
    try {
      const departments = await getDepartments();
      res.status(200).json(departments);
    } catch (error) {
      res.status(500).json({ error: 'Failed to fetch departments' });
    }
  };
  
export const fetchBooks = async (req: Request, res: Response) => {
    try {
      const books = await getBooks();
      res.status(200).json(books);
    } catch (error) {
      res.status(500).json({ error: 'Failed to fetch books' });
    }
  };
