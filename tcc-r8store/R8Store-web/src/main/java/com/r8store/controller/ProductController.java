package com.r8store.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.primefaces.model.UploadedFile;

import com.r8store.enums.Enum_Pages;
import com.r8store.model.dao.ProductDAO;
import com.r8store.model.entity.Product;


@Named
@ViewScoped
public class ProductController extends Controller implements Serializable {
	
	private static final long serialVersionUID = 1443635620601894345L;
	
	@Inject
	private UserController userController;
	
	@Inject
	private ProductDAO pDAO;
	
	private Product product;
	private List<Product> products;
	private Product selectedProduct;
	
	private UploadedFile file;
	
	@PostConstruct
	public void init() {
		product = new Product();
		selectedProduct = new Product();
		products = new ArrayList<>();
	}
	
	public void createAddProductPage(ComponentSystemEvent event) {
		if (!this.verifyPageAccess(userController.getMeta(), Enum_Pages.EMPLOYEEPAGES)) {
			return;
		}
	}
	
	public void createListProductPage(ComponentSystemEvent event){
		if (!this.verifyPageAccess(userController.getMeta(), Enum_Pages.EMPLOYEEPAGES)) {
			return;
		}
		if (this.userController.getUsuarioLogado().getStore() != null) {
			this.products = pDAO.findProductsByStore(this.userController.getUsuarioLogado().getStore().getId());
		} else {
			this.products = pDAO.findProductsByStore(this.userController.getUsuarioLogado().getEmployee().getActualStore().getId());
		}
		
		if(this.getErrors() != null && this.getErrors().size() > 0) {
			this.executeCommand("PF('editDialog').show()");	
		}
	}
	
	public void showProduct(ComponentSystemEvent event){
		if (!this.verifyPageAccess(userController.getMeta(), Enum_Pages.PERSONPAGES)) {
			return;
		}
		
		Map<String, String> params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
		
		this.product = pDAO.findById(Long.parseLong(params.get("id")));
	}
	
	public void registerAction() {
		try {
			Map<String, String> validation = product.validate(false);
			if(this.file.getFileName() == null || this.file.getFileName().isEmpty()) {
				validation.put("image", "É necessário inserir uma imagem");
			}	
						
			if (validation.isEmpty()) {
				if(this.userController.getUsuarioLogado().getEmployee() != null) {
					this.product.setStore(this.userController.getUsuarioLogado().getEmployee().getActualStore());
				} else {
					this.product.setStore(this.userController.getUsuarioLogado().getStore());
				}				
				this.product = pDAO.merge(product);
				
				product.setFile(this.file);
				product.upload("imagemPrincipal");
				
				this.product = new Product();
				this.addMessage("Sucesso", "Cadastro realizado com sucesso.");
				this.setErrors(null);				
				return;
			} else {
				this.setErrors(validation); 
				return;
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delete() {		
		pDAO.removeById(this.selectedProduct.getId());
		
		products.remove(this.selectedProduct);
		this.addMessage("Sucesso", "Sua exclusão foi realizada com sucesso.");
		
		return;
	}	
	
	public void update() {			
		Map<String, String> validation = this.selectedProduct.validate(true);
		
		if (validation.isEmpty()) {
			pDAO.merge(this.selectedProduct);
			
			if(this.file != null && this.file.getFileName() != null && !this.file.getFileName().isEmpty()) {
				selectedProduct.setFile(this.file);
				selectedProduct.upload("imagemPrincipal");
			}
			
			this.selectedProduct = new Product();
			this.addMessage("Sucesso", "Sua alteração foi realizada com sucesso.");
			this.setErrors(null);
			this.executeCommand("PF('editDialog').hide()");			
			return;
		} else {
			this.setErrors(validation);
			return;
		}
		
	}	

	public UserController getUserController() {
		return userController;
	}

	public void setUserController(UserController userController) {
		this.userController = userController;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(Product selectedProduct) {
		this.setErrors(null);
		this.selectedProduct = selectedProduct;
	}

}
