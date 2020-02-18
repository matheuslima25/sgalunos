package br.com.hero.sgalunos.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Usuario extends BaseIdEntity implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Column(unique = true)
	private String email;

	@Column(unique = true)
	private String username;

	private String primeiroNome;
	private String ultimoNome;

	private boolean enabled;

	@Column(name = "account_locked")
	private boolean accountNonLocked;

	@Column(name = "account_expired")
	private boolean accountNonExpired;

	@Column(name = "credentials_expired")
	private boolean credentialsNonExpired;

	@JsonIgnore
	private String password;

	private String telefone;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;

	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "role_usuario", joinColumns = {
			@JoinColumn(name = "usuario_id", referencedColumnName = "id")}, inverseJoinColumns = {
			@JoinColumn(name = "role_id", referencedColumnName = "id")
	})
	private List<Role> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();

		roles.forEach(r -> {
			authorities.add(new SimpleGrantedAuthority(r.getNome()));
			r.getPermissoes().forEach(p -> {
				authorities.add(new SimpleGrantedAuthority(p.getNome()));
			});
		});
		return authorities
	}

	@Override
	public boolean isAccountNonExpired() {
		return !accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !accountNonExpired;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

}
