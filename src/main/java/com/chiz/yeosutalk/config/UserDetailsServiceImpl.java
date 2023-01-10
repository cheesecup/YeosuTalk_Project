package com.chiz.yeosutalk.config;

import com.chiz.yeosutalk.domain.Member;
import com.chiz.yeosutalk.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByAccountId(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        return new User(member.getAccountId(), member.getPwd(), grantedAuthorities);
    }
}
