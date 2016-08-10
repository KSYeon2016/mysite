select * from BOARD;
insert into BOARD values(seq_board.nextval, '테스트2', '테스트', sysdate, 0, (select max(GROUP_NO) from BOARD)+1, 1, 1, 2);
select max(GROUP_NO) from BOARD;
commit;

select title, content from BOARD where no=1;

update BOARD set title='둘리3', content='테스트' where no=5;
commit;

update BOARD set VIEW_COUNT=(select VIEW_COUNT from BOARD where no=5)+1 where no=5;
select VIEW_COUNT from BOARD where no=5;

select no, title, content, reg_date, view_count, name, user_no
  from (select b.no, b.title, b.content, to_char(b.reg_date, 'yyyy-mm-dd pm hh12:mi:ss') as reg_date, b.view_count, u.NAME, b.user_no   
          from board b, users u 
          where b.USER_NO = u.NO
          order by b.REG_DATE desc) a
  where ROWNUM >= 1 and ROWNUM <= 5;
  
select b.no, b.title, b.content, to_char(b.reg_date, 'yyyy-mm-dd pm hh12:mi:ss') as reg_date, b.view_count, u.NAME, b.user_no, ROWNUM
          from board b, users u 
          where b.USER_NO = u.NO
          order by b.REG_DATE desc;