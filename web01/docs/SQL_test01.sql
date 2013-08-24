0. 준비
ALTER SESSION SET NLS_DATE_FORMAT='YYYY-MM-DD';

1.
SELECT empno, ename, sal
FROM emp
WHERE deptno=30

2.
SELECT ename, deptno, sal
FROM emp
WHERE hiredate = '1983-01-12'

3.
SELECT *
FROM emp
WHERE job <> 'MANAGER'

4.
SELECT empno, ename
FROM emp
WHERE ename > 'K%'
	AND ename NOT LIKE 'K%'

5.
SELECT ename, job, sal
FROM emp
WHERE sal > 1600 AND sal < 3000

6.
SELECT empno, ename, deptno
FROM emp
WHERE ename LIKE '%S%'

7.
SELECT *
FROM emp
WHERE ename LIKE '_A%'

8.
SELECT ename, deptno
FROM emp
WHERE comm IS NULL

9.
SELECT *
FROM emp
WHERE deptno=30
ORDER BY sal DESC, ename ASC

10.
/* emp의 부서번호와 일치하는게 없으면 해당 사원은 제외 */
SELECT e.ENAME, d.DNAME
FROM emp e join dept d
	USING (deptno)

/* emp의 부서번호와 일치하는게 없으면 해당 사원은 제외 */
SELECT e.ENAME, d.DNAME
FROM emp e inner join dept d
	on e.deptno=d.deptno

/* emp의 부서번호와 일치하는게 없어도 사원 정보 출력 */
SELECT e.ENAME, d.DNAME
FROM emp e left outer join dept d
	on e.deptno=d.deptno

11.
/* emp의 부서번호와 일치하는게 없는 사원도 포함 */
SELECT d.DNAME, e.ename
FROM emp e left outer join dept d
	on (e.DEPTNO=d.DEPTNO)
WHERE e.ENAME='홍길동'

/* emp의 부서번호와 일치하는 사원을 제외 했기 때문에, 조회 안됨*/
SELECT d.DNAME, e.ename
FROM emp e join dept d
	USING(deptno)
WHERE e.ENAME='홍길동'


SELECT dname
FROM dept
WHERE deptno=(SELECT deptno FROM emp WHERE ename='홍길동')

12.
SELECT e.ename, d.DNAME
FROM emp e join dept d
	USING (deptno)
WHERE deptno IN (SELECT DISTINCT deptno FROM emp WHERE sal >= 3000)

13.
SELECT e.ename, d.DNAME, e.HIREDATE, d.LOC
FROM emp e join dept d
	USING (deptno)
WHERE DEPTNO=10
	AND job IN (SELECT DISTINCT job FROM emp WHERE deptno=20)

14.
SELECT e.empno, e.ename, d.DNAME, e.HIREDATE, d.LOC
FROM emp e join dept d
	USING (deptno)
WHERE DEPTNO=10
	AND job NOT IN (SELECT DISTINCT job FROM emp WHERE deptno=30)

15.
SELECT ename, sal
FROM emp
WHERE sal IN (SELECT sal FROM emp WHERE ename='MARTIN' OR ename='SCOTT')

16.
SELECT e.ename, e.sal, e.HIREDATE, d.LOC
FROM emp e join dept d
	USING (deptno)
WHERE deptno = (SELECT deptno FROM emp WHERE ename='JONES')

17.
SELECT e.ename, m.ename, d.dname, d.loc
FROM emp e, emp m, dept d
WHERE
	e.mgr=m.empno(+)
  AND e.deptno=d.deptno
	AND e.deptno IN (20, 30)

SELECT e.ename, m.ename, d.dname, d.loc
FROM emp e left outer JOIN emp m
	ON e.mgr=m.empno
  left outer JOIN dept d
  ON e.deptno=d.deptno
WHERE e.deptno IN (20, 30)

18.
SELECT e.ename, d.dname, e.sal
FROM emp e left outer JOIN dept d
	ON e.deptno=d.deptno
WHERE sal < (SELECT max(sal) FROM emp WHERE deptno=30)

SELECT e2.ename, d.dname, e2.sal
FROM
  (SELECT e.ename, e.deptno, e.sal
  FROM emp e
  WHERE e.sal < (SELECT max(sal) FROM emp WHERE deptno=30)
  ) e2,
  dept d
WHERE e2.deptno=d.deptno(+)

SELECT e2.ename, d.dname, e2.sal
FROM
  (SELECT e.ename, e.deptno, e.sal
  FROM emp e
  WHERE e.sal < (SELECT max(sal) FROM emp WHERE deptno=30)
  ORDER BY ename asc
  ) e2 left outer JOIN dept d
	ON e2.deptno=d.deptno

19.
SELECT e.ename, d.dname, d.loc, e.sal
FROM emp e join dept d
	USING (deptno)
WHERE deptno NOT in (SELECT DISTINCT deptno FROM emp WHERE sal >= 3000)

20.
SELECT e.ename, m.ename, e.sal, d.dname, d.loc
FROM emp e, dept d, emp m
WHERE
	e.deptno=d.deptno
  AND e.mgr=m.empno(+)
	AND e.deptno in (SELECT DISTINCT deptno FROM emp WHERE sal >= 3000)

SELECT e.ename, m.ename, e.sal, d.dname, d.loc
FROM emp e left outer join dept d ON e.deptno=d.DEPTNO
	left outer JOIN emp m ON e.mgr=m.empno
WHERE e.deptno in (SELECT DISTINCT deptno FROM emp WHERE sal >= 3000)



