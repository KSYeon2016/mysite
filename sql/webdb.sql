insert into BOARD values(seq_board.nextval, '테스트2', '테스트', sysdate, 0, (select max(GROUP_NO) from BOARD)+1, 1, 1, 2);
select max(GROUP_NO) from BOARD;
select title, content from BOARD where no=1;

update BOARD set title='둘리3', content='테스트' where no=5;
commit;

update BOARD set VIEW_COUNT=(select VIEW_COUNT from BOARD where no=5)+1 where no=5;
select VIEW_COUNT from BOARD where no=5;

select no, title, content, reg_date, view_count, name, user_no, rn, group_no, order_no, depth
  from (select a.*, ROWNUM as rn from (select b.no, b.title, b.content, to_char(b.reg_date, 'yyyy-mm-dd pm hh12:mi:ss') as reg_date, b.view_count, b.group_no, b.order_no, b.depth, u.NAME, b.user_no
          from board b, users u 
          where b.USER_NO = u.NO
          order by group_no desc, order_no asc) a where a.title like '%%')
  where rn >= 1 and rn <= 5
  order by group_no desc, order_no asc;

select a.*, ROWNUM as rn from (select b.no, b.title, b.content, to_char(b.reg_date, 'yyyy-mm-dd pm hh12:mi:ss') as reg_date, b.view_count, b.group_no, b.order_no, b.depth, u.NAME, b.user_no
          from board b, users u 
          where b.USER_NO = u.NO
          order by b.REG_DATE desc) a where a.title like '%테%';
          
select b.no, b.title, b.content, to_char(b.reg_date, 'yyyy-mm-dd pm hh12:mi:ss') as reg_date, b.view_count, b.group_no, b.order_no, b.depth, u.NAME, b.user_no
          from board b, users u 
          where b.USER_NO = u.NO
          order by group_no desc, order_no asc;

insert into BOARD values(seq_board.nextval, '댓글', '테스트입니다', sysdate, 0, (select group_no from board where no = 1), (select order_no from BOARD where no=1)+1, (select depth from BOARD where no=1)+1, 2);
update BOARD set order_no = order_no+1 where group_no=(select group_no from BOARD where no = (select max(no) from BOARD)) and order_no=(select order_no from BOARD where no = (select max(no) from BOARD)) and no <> (select max(no) from BOARD);
commit;
select * from board order by group_no desc, order_no asc;