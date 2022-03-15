package des.kanban.security;

import java.io.IOException;



import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import des.kanban.entidades.Usuario;
import des.kanban.servicios.UsuarioServicio;




public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler{
	@Autowired
	private UsuarioServicio userService;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		HttpSession session = request.getSession();
		Usuario authUser = userService.obtenerUsuarioPorNombre(userDetails.getUsername());
		session.setAttribute("usuario", authUser);
		session.setAttribute("usuario.nombre", authUser.getNombreUsuario());
		session.setAttribute("usuario.id", authUser.getIdUsuario());

		boolean isTrabajador = false;
		boolean isManager = false;
		
		final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (final GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("trabajador")) {
				isTrabajador = true;
				session.setAttribute("usuario.roles", grantedAuthority.getAuthority().equals("trabajador"));
				break;
			}else if (grantedAuthority.getAuthority().equals("manager")) {
				isManager = true;
				session.setAttribute("usuario.roles", grantedAuthority.getAuthority().equals("manager"));
				break;
			}
		}

		
		String targetUrl;
		if (isTrabajador) {
			targetUrl = "/index";
		} else if (isManager) {
			targetUrl = "/index";
		} else {
			throw new IllegalStateException();
		}
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	public void setRedirectStrategy(final RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}
//	https://programmer.ink/think/spring-security-user-service-userdetailsservice-source-code-analysis.html --->para borrar y actualizar usuario
}
