Second Highest salary :  https://leetcode.com/problems/second-highest-salary/submissions/
  Select max(salary) as SecondHighestSalary
  from Employee
  where salary < (select max(salary) from Employee);

Nth highest Salary  : https://leetcode.com/problems/nth-highest-salary/ 
  CREATE FUNCTION getNthHighestSalary(N IN NUMBER) RETURN NUMBER IS
  result NUMBER;
  BEGIN
      /* Write your PL/SQL query statement below */
      SELECT T.salary into result 
      FROM (SELECT e.Salary, ROW_NUMBER() OVER(order by Salary desc) rn FROM Employee e)  T 
      WHERE T.rn = N;
      RETURN result;
  END;
 
Employees Earning More Than Their Managers : 
  Select a.Name
  From Employee a 
  JOIN Employee b
  ON a.ManagerId = b.Id
  Where a.Salary > b.Salary;

Customers Who Never Order
  Select C.Name As Customers
  From Customers C 
  left Join Orders O
  On C.Id = O.CustomerId
  Where O.CustomerId is null;
