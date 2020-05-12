package cn.ann.financial.manager.business.oauth2.service;

import cn.ann.financial.manager.provider.user.api.TbUserServiceApi;
import cn.ann.financial.manager.provider.user.domain.TbRole;
import cn.ann.financial.manager.provider.user.domain.TbUser;
import com.google.common.collect.Lists;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * Create By 88475 With IntelliJ IDEA On 2020-3-22:2:10
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    @Reference(version = "1.0.0")
    private TbUserServiceApi userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (s != null) {
            TbUser user = userService.get(s);
            if (user != null) {
                List<TbRole> roles = userService.getRoles(user.getId());
                List<GrantedAuthority> authorities = Lists.newArrayList();
                return new User(user.getUsername(), user.getPassword(), authorities);
            }
        }
        return null;
    }
}
