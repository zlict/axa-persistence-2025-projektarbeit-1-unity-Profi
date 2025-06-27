const URL = 'http://localhost:8080';
const FORM_MODES = { NEW: 'new', EDIT: 'edit' };
let formMode = FORM_MODES.NEW;
let customerForm;
let customers = [];

const saveCustomer = (e) => {
  e.preventDefault();
  const formData = new FormData(e.target);
  const customer = {
    firstName: formData.get('firstName'),
    lastName: formData.get('lastName'),
    dateOfBirth: formData.get('dateOfBirth'),
    adress: formData.get('adress'),
    email: formData.get('email')
  };
  if (formMode === FORM_MODES.NEW) {
    fetch(`${URL}/customers`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(customer)
    }).then(result => result.json()).then(newCustomer => {
      customers.push(newCustomer);
      renderCustomers();
    });
  } else if (formMode === FORM_MODES.EDIT) {
    fetch(`${URL}/customers/${formData.get('id')}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(customer)
    }).then(result => result.json()).then(updatedCustomer => {
      customers = customers.map(c => c.id == updatedCustomer.id ? updatedCustomer : c);
      renderCustomers();
    });
  }
  formMode = FORM_MODES.NEW;
  e.target.reset();
};

const deleteCustomer = (id) => {
  fetch(`${URL}/customers/${id}`, { method: 'DELETE' }).then(() => {
    customers = customers.filter(c => c.id != id);
    renderCustomers();
  });
};

const editCustomer = (id) => {
  formMode = FORM_MODES.EDIT;
  const customer = customers.find(c => c.id == id);
  customerForm.querySelector('input[name=id]').value = customer.id;
  customerForm.querySelector('input[name=firstName]').value = customer.firstName;
  customerForm.querySelector('input[name=lastName]').value = customer.lastName;
  customerForm.querySelector('input[name=dateOfBirth]').value = customer.dateOfBirth;
  customerForm.querySelector('input[name=adress]').value = customer.adress;
  customerForm.querySelector('input[name=email]').value = customer.email;
};

const indexCustomers = () => {
  fetch(`${URL}/customers`).then(r => r.json()).then(result => {
    customers = result;
    renderCustomers();
  });
  renderCustomers();
};

const createCell = (text) => {
  const cell = document.createElement('td');
  cell.innerText = text;
  return cell;
};

const createActionsCell = (id) => {
  const cell = document.createElement('td');
  const deleteButton = document.createElement('button');
  deleteButton.innerText = "Delete";
  deleteButton.addEventListener('click', () => deleteCustomer(id));
  cell.appendChild(deleteButton);
  const editButton = document.createElement('button');
  editButton.innerText = "Edit";
  editButton.addEventListener('click', () => editCustomer(id));
  cell.appendChild(editButton);
  return cell;
};

const renderCustomers = () => {
  const display = document.querySelector('#customerDisplay');
  display.innerHTML = '';
  customers.forEach(customer => {
    const row = document.createElement('tr');
    row.appendChild(createCell(customer.id));
    row.appendChild(createCell(customer.firstName));
    row.appendChild(createCell(customer.lastName));
    row.appendChild(createCell(customer.dateOfBirth));
    row.appendChild(createCell(customer.adress));
    row.appendChild(createCell(customer.email));
    row.appendChild(createActionsCell(customer.id));
    display.appendChild(row);
  });
};

document.addEventListener('DOMContentLoaded', function () {
  customerForm = document.querySelector('#customerForm');
  customerForm.addEventListener('submit', saveCustomer);
  indexCustomers();
});
