package service;

import dao.CustomerDao;
import domain.Customer;
import domain.PageBean;

import java.util.List;

public class CustomerService
{
    CustomerDao customerDao=new CustomerDao();

    public void add(Customer customer)
    {
        customerDao.add(customer);
    }


    public PageBean<Customer> findAll(int pc,int pr)
    {
        return customerDao.findAll(pc,pr);
    }

    public Customer find(String id)
    {
        return customerDao.find(id);
    }

    public void edit(Customer customer)
    {
        customerDao.edit(customer);
    }

    public void delete(String id)
    {
        customerDao.delete(id);
    }

    public PageBean<Customer> query(Customer customer,int pc,int pr)
    {
        return customerDao.query(customer,pc,pr);


    }

}
