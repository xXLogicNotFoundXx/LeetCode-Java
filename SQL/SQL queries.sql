--Second Highest salary :  https://leetcode.com/problems/second-highest-salary/submissions/
  Select max(salary) as SecondHighestSalary
  from Employee
  where salary < (select max(salary) from Employee);

--Nth highest Salary  : https://leetcode.com/problems/nth-highest-salary/ 
  CREATE FUNCTION getNthHighestSalary(N IN NUMBER) RETURN NUMBER IS
  result NUMBER;
  BEGIN
      /* Write your PL/SQL query statement below */
      SELECT T.salary into result 
      FROM (SELECT e.Salary, ROW_NUMBER() OVER(order by Salary desc) rn FROM Employee e)  T 
      WHERE T.rn = N;
      RETURN result;
  END;
 
--Employees Earning More Than Their Managers : https://leetcode.com/problems/employees-earning-more-than-their-managers/
  Select a.Name
  From Employee a 
  JOIN Employee b
  ON a.ManagerId = b.Id
  Where a.Salary > b.Salary;

-- Customers Who Never Ordered : https://leetcode.com/problems/customers-who-never-order/
  Select C.Name As Customers
  From Customers C 
  left Join Orders O
  On C.Id = O.CustomerId
  Where O.CustomerId is null;
  

--  Classes More Than 5 Students : https://leetcode.com/problems/classes-more-than-5-students/
  select class 
  from courses
  group by class
  having count(distinct student)>=5;  -- OR  count(class)>=5 if the students are distinct already
  
  -- Duplicate emails 
  Select Email From Person
  Group by Email
  Having count(Email) > 1;
  
  
-- 178. Rank Scores  : https://leetcode.com/problems/rank-scores/
  SELECT Score, (SELECT count(distinct Score) FROM Scores WHERE Score >= s.Score) Rank
  FROM Scores s
  ORDER BY Score desc;

-- 184. Department Highest Salary
  
-- find average salary for each department with more than five members
  select DEPARTMENT,count(STAFF_ID) as CountStaff, avg(SALARY) as AVGSalary
  from STAFF
  group by DEPARTMENT
  having count(STAFF_ID) > 5
  
