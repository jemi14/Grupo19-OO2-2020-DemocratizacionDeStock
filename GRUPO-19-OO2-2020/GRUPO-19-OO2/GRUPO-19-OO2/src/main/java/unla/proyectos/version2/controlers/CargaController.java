package unla.proyectos.version2.controlers;




import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.view.RedirectView;

import unla.proyectos.version2.helpers.ViewRouteHelper;
import unla.proyectos.version2.models.ClienteModel;
import unla.proyectos.version2.models.GerenteModel;
import unla.proyectos.version2.models.LocalModel;
import unla.proyectos.version2.models.ProductoModel;
import unla.proyectos.version2.models.StockModel;
import unla.proyectos.version2.models.VendedorModel;
import unla.proyectos.version2.services.IClienteService;
import unla.proyectos.version2.services.IGerenteService;
import unla.proyectos.version2.services.ILocalService;
import unla.proyectos.version2.services.IProductoService;
import unla.proyectos.version2.services.IStockService;
import unla.proyectos.version2.services.IVendedorService;






@Controller
@RequestMapping("/cargar")
public class CargaController {
	
	@Autowired
	@Qualifier("gerenteService")
	private IGerenteService gerenteService;
	
	@Autowired
	@Qualifier("clienteService")
	private IClienteService clienteService;
	
	@Autowired
	@Qualifier("vendedorService")
	private IVendedorService vendedorService;
	
	@Autowired
	@Qualifier("productoService")
	private IProductoService productoService;
	
	@Autowired
	@Qualifier("localService")
	private ILocalService localService;
	
	
	@Autowired
	@Qualifier("stockService")
	private IStockService stockService;
	

	;
	
	@GetMapping("/")
	public RedirectView redirectToHomeIndex() {
		
		

		GerenteModel g1 = new GerenteModel("Griselda", "Benitez", null, 1111111,  120000 );
		GerenteModel g2 = new GerenteModel("Leandro", "Haugg", null, 1222222,  220000 );
		GerenteModel g3 = new GerenteModel("Ramiro", "Benitez", null, 133333, 320000 );
		GerenteModel g4 = new GerenteModel("Lautaro", "Braum", null, 1444444,  120000 );
		GerenteModel g5= new GerenteModel("Alberto", "Iglesias", null, 155555,  220000 );
		GerenteModel g6 = new GerenteModel("Aldana", "Altamiranno", null, 1666666, 320000 );
		
		VendedorModel v1 = new VendedorModel("Jorge", "Garcia", null, 2111111,12000, null,0 );
		VendedorModel v2 = new VendedorModel("Raul", "Baldi", null,  2222222,13330, null,11 );
		VendedorModel v3 = new VendedorModel("Raul", "Jaramillo", null, 233333,15000, null,23 );
				
		
		ClienteModel c1 = new ClienteModel("Raul", "Ortiz", null, 3111111, "r@mail");
		ClienteModel c2 = new ClienteModel("Lara", "Muniz", null, 3222222, "l@mail");
		ClienteModel c3 = new ClienteModel("Lucas", "Perez", null, 3233333, "ul@mail");
		
		
		ProductoModel p1 = new ProductoModel( "EL mas rico, lo vale...", 123.5f,"Chocolate");
		ProductoModel p2 = new ProductoModel( "Baratito pero delicioso", 13.5f,"Kinder");
		ProductoModel p3 = new ProductoModel( "Duro pero rico", 11f,"Caramelo");
		ProductoModel p4 = new ProductoModel( "Sabor del conurbano", 15.1f,"Helado");
		ProductoModel p5 = new ProductoModel( "1kg de las mejores pepas", 20f,"Pepas");
		
		
		vendedorService.insert(v1);vendedorService.insert(v2);vendedorService.insert(v3);
		clienteService.insert(c1);clienteService.insert(c2);clienteService.insert(c3);
		gerenteService.insertOrUpdate(g1);gerenteService.insertOrUpdate(g2);gerenteService.insertOrUpdate(g3);
		gerenteService.insertOrUpdate(g4);gerenteService.insertOrUpdate(g5);gerenteService.insertOrUpdate(g6);
		
		productoService.insert(p1);productoService.insert(p2);productoService.insert(p3);
		productoService.insert(p4);productoService.insert(p5);
		
		
		
		
		LocalModel local1 = new LocalModel("Banfield", "Rodriguez", 1111,1234l,-34.42f,-58.23f,gerenteService.findByIdPersona(7));
		LocalModel local2 = new LocalModel("Lanus", "Alfonsin", 22222,12224l,-34.45f,-58.22f,gerenteService.findByIdPersona(8));
		LocalModel local3 = new LocalModel("Resistencia", "Chaco", 333,12224l,-27.45f,-58.98f,gerenteService.findByIdPersona(9));
		LocalModel local4 = new LocalModel("Santa Rosa", "Peron", 1111,1234l,-36.6167f,-64.2833f,gerenteService.findByIdPersona(10));
		LocalModel local5 = new LocalModel("Bariloche", "Cristina F.", 22222,12224l,-41.14556f,-71.3082199f,gerenteService.findByIdPersona(11));
		LocalModel local6 = new LocalModel("Rosario", "Sarmiento", 333,12224l,-32.94681f,-60.6393f,gerenteService.findByIdPersona(12));
		
		localService.insertOrUpdate(local1);
		localService.insertOrUpdate(local2);
		localService.insertOrUpdate(local3);
		localService.insertOrUpdate(local4);
		localService.insertOrUpdate(local5);
		localService.insertOrUpdate(local6);

		
		//Invento fechas
		 LocalDate inicial = LocalDate.of(2019, Month.JANUARY, 1);
		 long days = ChronoUnit.DAYS.between(inicial, LocalDate.now());
		
		ArrayList<LocalDate> fechas = new ArrayList<LocalDate>(); 
		
		 for (int i = 1; i<=30; i++) {
			 
			 LocalDate randomDate = inicial.plusDays(new Random().nextInt((int) days + 1));
			 
			 fechas.add(randomDate);
			 
		 }
		
		
		StockModel s1 = new StockModel(productoService.findById(1),localService.findById(1),12,fechas.get(0));
		StockModel s2 = new StockModel(productoService.findById(1),localService.findById(2),11,fechas.get(1));
		StockModel s3 = new StockModel(productoService.findById(1),localService.findById(3),12,fechas.get(2));
		StockModel s4 = new StockModel(productoService.findById(1),localService.findById(4),11,fechas.get(3));
		StockModel s5 = new StockModel(productoService.findById(1),localService.findById(5),12,fechas.get(4));
		StockModel s6 = new StockModel(productoService.findById(1),localService.findById(6),11,fechas.get(5));
		
		
		StockModel s7 = new StockModel(productoService.findById(2),localService.findById(1),4,fechas.get(6));
		StockModel s8 = new StockModel(productoService.findById(2),localService.findById(2),120,fechas.get(7));
		StockModel s9 = new StockModel(productoService.findById(2),localService.findById(3),11,fechas.get(8));
		StockModel s10 = new StockModel(productoService.findById(2),localService.findById(4),41,fechas.get(9));
		StockModel s11 = new StockModel(productoService.findById(2),localService.findById(5),10,fechas.get(10));
		StockModel s12 = new StockModel(productoService.findById(2),localService.findById(6),101,fechas.get(11));
		
		
		StockModel s13= new StockModel(productoService.findById(3),localService.findById(1),2,fechas.get(12));
		StockModel s14 = new StockModel(productoService.findById(3),localService.findById(2),2,fechas.get(13));
		StockModel s15 = new StockModel(productoService.findById(3),localService.findById(3),2,fechas.get(14));
		StockModel s16= new StockModel(productoService.findById(3),localService.findById(4),2,fechas.get(15));
		StockModel s17 = new StockModel(productoService.findById(3),localService.findById(5),2,fechas.get(16));
		StockModel s18 = new StockModel(productoService.findById(3),localService.findById(6),2,fechas.get(17));
		
		
		StockModel s19 = new StockModel(productoService.findById(4),localService.findById(1),2,fechas.get(18));
		StockModel s20 = new StockModel(productoService.findById(4),localService.findById(2),10,fechas.get(19));
		StockModel s21 = new StockModel(productoService.findById(4),localService.findById(3),21,fechas.get(20));
		StockModel s22 = new StockModel(productoService.findById(4),localService.findById(4),20,fechas.get(21));
		StockModel s23 = new StockModel(productoService.findById(4),localService.findById(5),13,fechas.get(22));
		StockModel s24 = new StockModel(productoService.findById(4),localService.findById(6),20,fechas.get(23));
		
		
		StockModel s25 = new StockModel(productoService.findById(5),localService.findById(1),102,fechas.get(24));
		StockModel s26 = new StockModel(productoService.findById(5),localService.findById(2),12,fechas.get(25));
		StockModel s27 = new StockModel(productoService.findById(5),localService.findById(3),2,fechas.get(26));
		StockModel s28 = new StockModel(productoService.findById(5),localService.findById(4),21,fechas.get(27));
		StockModel s29 = new StockModel(productoService.findById(5),localService.findById(5),43,fechas.get(28));
		StockModel s30 = new StockModel(productoService.findById(5),localService.findById(6),14,fechas.get(29));
		
		
	
		
		
		stockService.insert(s1);stockService.insert(s2);stockService.insert(s3);
		stockService.insert(s4);stockService.insert(s5);stockService.insert(s6);
		stockService.insert(s7);stockService.insert(s8);
		stockService.insert(s9);stockService.insert(s10);stockService.insert(s11);
		stockService.insert(s12);stockService.insert(s13);stockService.insert(s14);
		stockService.insert(s15);stockService.insert(s16);
		stockService.insert(s17);stockService.insert(s18);stockService.insert(s19);
		stockService.insert(s20);stockService.insert(s21);stockService.insert(s22);
		stockService.insert(s23);stockService.insert(s24);
		stockService.insert(s25);stockService.insert(s26);stockService.insert(s27);
		stockService.insert(s28);stockService.insert(s29);stockService.insert(s30);
	
				
				
		

				
		
		
		
		
		
		
		
		return new RedirectView(ViewRouteHelper.PRODUCTOS_ROOT);
	}
}