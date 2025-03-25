package controller;

import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO;
import model.JavaBeans;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class Controller.
 */
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/reporter" })
public class Controller extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The dao. */
	DAO dao = new DAO();
	
	/** The contacto. */
	JavaBeans contacto = new JavaBeans();

	/**
	 * Instantiates a new controller.
	 */
	public Controller() {
		super();
	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		if (action.equals("/main")) {
			contacto(request, response);

		} else if (action.equals("/insert")) {
			adicionarContacto(request, response);

		} else if (action.equals("/select")) {
			listarContacto(request, response);

		} else if (action.equals("/update")) {
			editarContacto(request, response);

		} else if (action.equals("/delete")) {
			removerContacto(request, response);

		}

		else if (action.equals("/reporter")) {
			gerarRelatorio(request, response);

		}

		else {

			response.sendRedirect("index.html");
		}
	}

	
	
	/**
	 * Contacto.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void contacto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<JavaBeans> lista = dao.listarContacto();

		request.setAttribute("contactos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);

	}
	
	
	
	

	/**
	 * Adicionar contacto.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void adicionarContacto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		contacto.setNome(request.getParameter("nome"));
		contacto.setFone(request.getParameter("fone"));
		contacto.setEmail(request.getParameter("email"));

		dao.inserirContacto(contacto);

		response.sendRedirect("main");
	}

	
	
	
	/**
	 * Listar contacto.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void listarContacto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		contacto.setIdcon(request.getParameter("idcon"));
		dao.selecionarContacto(contacto);
		request.setAttribute("idcon", contacto.getIdcon());
		request.setAttribute("nome", contacto.getNome());
		request.setAttribute("fone", contacto.getFone());
		request.setAttribute("email", contacto.getEmail());

		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);

	}
	
	
	

	/**
	 * Editar contacto.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void editarContacto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		contacto.setIdcon(request.getParameter("idcon"));
		contacto.setNome(request.getParameter("nome"));
		contacto.setFone(request.getParameter("fone"));
		contacto.setEmail(request.getParameter("email"));

		dao.alterarContacto(contacto);

		response.sendRedirect("main");

	}
	
	
	

	/**
	 * Remover contacto.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void removerContacto(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		contacto.setIdcon(request.getParameter("idcon"));
		dao.deletarContacto(contacto);
		response.sendRedirect("main");
	}

	
	
	
	/**
	 * Gerar relatorio.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Document document = new Document();
		try {

			response.setContentType("apllication/pdf");

			response.addHeader("Content-Disposition", "inline; filename=" + "contactos.pdf");

			PdfWriter.getInstance(document, response.getOutputStream());

			document.open();
			document.add(new Paragraph("Lista de contactos:"));
			document.add(new Paragraph(" "));
			PdfPTable tabela = new PdfPTable(3);

			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("E-mail"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);

			ArrayList<JavaBeans> listar = dao.listarContacto();
			for (int i = 0; i < listar.size(); i++) {
				tabela.addCell(listar.get(i).getNome());
				tabela.addCell(listar.get(i).getFone());
				tabela.addCell(listar.get(i).getEmail());

			}

			document.add(tabela);
			document.close();
		} catch (Exception e) {
			System.out.println();
			document.close();
		}

	}
}
