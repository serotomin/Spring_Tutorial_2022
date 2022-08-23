package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository{

    private final JdbcTemplate jdbcTemplate;

    //@Autowired //아래 생성자가 단 1개만 있다면 @Autowired는 생략할 수 있다.
    public JdbcTemplateMemberRepository(DataSource dataSource) { //DataSource Injection(주입)
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate); //SimpleJdbcInsert 활용하여 쿼리없이 jdbc템플릿을 활용하여 멤버테이블에 id, name을 INSERT함
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) { //jdbcTemplate 메서드 패턴 + memberRowMapper 메서드 콜백을 활용한 디자인 패턴
        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id); //결과값 memberRowMapper에서 반환받아 리스트형태로 result에 저장
        return result.stream().findAny(); //Optional로 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
        return result.stream().findAny(); //Optional로 반환
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }

    private RowMapper<Member> memberRowMapper() { //리스트 형태로 멤버 객체 생성, set id, name
        return (rs, rowNum) -> { //람다 스타일로 변환, return new RowMapper<Member>() {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        };
    }
}

