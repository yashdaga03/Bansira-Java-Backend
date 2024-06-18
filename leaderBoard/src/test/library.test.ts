import request from 'supertest';
import axios from 'axios';
import app from '../app';

jest.mock('axios');
const mockedAxios = axios as jest.Mocked<typeof axios>;

describe('Library API Endpoints', () => {
  it('GET /api/leader/departments should return the department with maximum hits', async () => {
    const departmentsData = [
      { id: '66707ecb9a4a383101cf3029', name: 'Maths', departmentHits: 9, books: [] },
      { id: '667080803317dd1415070a9a', name: 'Computer', departmentHits: 8, books: [] }
    ];
    mockedAxios.get.mockResolvedValueOnce({ data: departmentsData });

    const res = await request(app).get('/api/leader/departments');
    expect(res.statusCode).toEqual(200);
    expect(res.body).toEqual(departmentsData[0]);
  });

  it('GET /api/leader/books should return books data with highest hits', async () => {
    const booksData = {
      totalPages: 1,
      totalElements: 2,
      first: true,
      last: true,
      size: 20,
      content: [
        { id: '66707addd5561029862c1960', title: 'Computer', author: 'Yash', available: true, weeklyHit: 0, monthlyHit: 0, todayHit: 0, createdAt: '2024-06-17T18:05:17.649Z', updatedAt: '2024-06-17T18:05:17.649Z', issued: false },
        { id: '66707aeed5561029862c1961', title: 'Maths', author: 'Jay', available: true, weeklyHit: 9, monthlyHit: 9, todayHit: 0, createdAt: '2024-06-17T18:05:34.183Z', updatedAt: '2024-06-17T18:05:34.183Z', issued: true }
      ],
      number: 0,
      sort: { empty: true, sorted: false, unsorted: true },
      numberOfElements: 2,
      pageable: { pageNumber: 0, pageSize: 20, sort: { empty: true, sorted: false, unsorted: true }, offset: 0, paged: true, unpaged: false },
      empty: false
    };
    mockedAxios.get.mockResolvedValueOnce({ data: booksData });

    const res = await request(app).get('/api/leader/books');
    expect(res.statusCode).toEqual(200);
    expect(res.body).toEqual({
      weeklyHit: booksData.content[1],
      monthlyHit: booksData.content[1],
      dailyHit: booksData.content[0]
    });
  });
});
