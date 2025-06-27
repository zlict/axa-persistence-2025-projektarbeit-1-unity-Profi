const URL = 'http://localhost:8080';
const FORM_MODES = { NEW: 'new', EDIT: 'edit' };
let formMode = FORM_MODES.NEW;
let vertragForm;
let vertraege = [];
let customers = [];

const saveVertrag = (e) => {
  e.preventDefault();
  const formData = new FormData(e.target);
  const vertrag = {
    policyNumber: formData.get('policyNumber'),
    productName: formData.get('productName'),
    startDate: formData.get('startDate'),
    endDate: formData.get('endDate'),
    customer: { id: formData.get('customerId') }
  };
  if (formMode === FORM_MODES.NEW) {
    fetch(`${URL}/vertraege`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(vertrag)
    }).then(result => result.json()).then(newVertrag => {
      vertraege.push(newVertrag);
      renderVertraege();
    });
  } else if (formMode === FORM_MODES.EDIT) {
    fetch(`${URL}/vertraege/${formData.get('vertragId')}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(vertrag)
    }).then(result => result.json()).then(updatedVertrag => {
      vertraege = vertraege.map(v => v.vertragId == updatedVertrag.vertragId ? updatedVertrag : v);
      renderVertraege();
    });
  }
  formMode = FORM_MODES.NEW;
  e.target.reset();
};

const deleteVertrag = (id) => {
  fetch(`${URL}/vertraege/${id}`, { method: 'DELETE' }).then(() => {
    vertraege = vertraege.filter(v => v.vertragId != id);
    renderVertraege();
  });
};

const editVertrag = (id) => {
  formMode = FORM_MODES.EDIT;
  const vertrag = vertraege.find(v => v.vertragId == id);
  vertragForm.querySelector('input[name=vertragId]').value = vertrag.vertragId;
  vertragForm.querySelector('input[name=policyNumber]').value = vertrag.policyNumber;
  vertragForm.querySelector('input[name=productName]').value = vertrag.productName;
  vertragForm.querySelector('input[name=startDate]').value = vertrag.startDate;
  vertragForm.querySelector('input[name=endDate]').value = vertrag.endDate;
  vertragForm.querySelector('select[name=customerId]').value = vertrag.customer?.id;
};

const indexVertraege = () => {
  fetch(`${URL}/vertraege`).then(r => r.json()).then(result => {
    vertraege = result;
    renderVertraege();
  });
  renderVertraege();
};

const indexCustomers = () => {
  fetch(`${URL}/customers`).then(r => r.json()).then(result => {
    customers = result;
    renderCustomers();
  });
};

const renderCustomers = () => {
  const select = document.querySelector('#customerSelect');
  select.innerHTML = '';
  customers.forEach(c => {
    const option = document.createElement('option');
    option.value = c.id;
    option.innerText = `${c.firstName} ${c.lastName}`;
    select.appendChild(option);
  });
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
  deleteButton.addEventListener('click', () => deleteVertrag(id));
  cell.appendChild(deleteButton);
  const editButton = document.createElement('button');
  editButton.innerText = "Edit";
  editButton.addEventListener('click', () => editVertrag(id));
  cell.appendChild(editButton);
  return cell;
};

const renderVertraege = () => {
  const display = document.querySelector('#vertragDisplay');
  display.innerHTML = '';
  vertraege.forEach(vertrag => {
    const row = document.createElement('tr');
    row.appendChild(createCell(vertrag.vertragId));
    row.appendChild(createCell(vertrag.policyNumber));
    row.appendChild(createCell(vertrag.productName));
    row.appendChild(createCell(vertrag.startDate));
    row.appendChild(createCell(vertrag.endDate));
    row.appendChild(createCell(vertrag.customer ? `${vertrag.customer.firstName} ${vertrag.customer.lastName}` : ''));
    row.appendChild(createActionsCell(vertrag.vertragId));
    display.appendChild(row);
  });
};

document.addEventListener('DOMContentLoaded', function () {
  vertragForm = document.querySelector('#vertragForm');
  vertragForm.addEventListener('submit', saveVertrag);
  indexVertraege();
  indexCustomers();
});
