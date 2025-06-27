const URL = 'http://localhost:8080';
const FORM_MODES = { NEW: 'new', EDIT: 'edit' };
let formMode = FORM_MODES.NEW;
let partnerForm;
let partners = [];

const savePartner = (e) => {
  e.preventDefault();
  const formData = new FormData(e.target);
  const partner = {
    name: formData.get('name'),
    partnerType: formData.get('partnerType'),
    address: formData.get('address'),
    phone: formData.get('phone')
  };
  if (formMode === FORM_MODES.NEW) {
    fetch(`${URL}/partners`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(partner)
    }).then(result => result.json()).then(newPartner => {
      partners.push(newPartner);
      renderPartners();
    });
  } else if (formMode === FORM_MODES.EDIT) {
    fetch(`${URL}/partners/${formData.get('partnerId')}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(partner)
    }).then(result => result.json()).then(updatedPartner => {
      partners = partners.map(p => p.partnerId == updatedPartner.partnerId ? updatedPartner : p);
      renderPartners();
    });
  }
  formMode = FORM_MODES.NEW;
  e.target.reset();
};

const deletePartner = (id) => {
  fetch(`${URL}/partners/${id}`, { method: 'DELETE' }).then(() => {
    partners = partners.filter(p => p.partnerId != id);
    renderPartners();
  });
};

const editPartner = (id) => {
  formMode = FORM_MODES.EDIT;
  const partner = partners.find(p => p.partnerId == id);
  partnerForm.querySelector('input[name=partnerId]').value = partner.partnerId;
  partnerForm.querySelector('input[name=name]').value = partner.name;
  partnerForm.querySelector('input[name=partnerType]').value = partner.partnerType;
  partnerForm.querySelector('input[name=address]').value = partner.address;
  partnerForm.querySelector('input[name=phone]').value = partner.phone;
};

const indexPartners = () => {
  fetch(`${URL}/partners`).then(r => r.json()).then(result => {
    partners = result;
    renderPartners();
  });
  renderPartners();
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
  deleteButton.addEventListener('click', () => deletePartner(id));
  cell.appendChild(deleteButton);
  const editButton = document.createElement('button');
  editButton.innerText = "Edit";
  editButton.addEventListener('click', () => editPartner(id));
  cell.appendChild(editButton);
  return cell;
};

const renderPartners = () => {
  const display = document.querySelector('#partnerDisplay');
  display.innerHTML = '';
  partners.forEach(partner => {
    const row = document.createElement('tr');
    row.appendChild(createCell(partner.partnerId));
    row.appendChild(createCell(partner.name));
    row.appendChild(createCell(partner.partnerType));
    row.appendChild(createCell(partner.address));
    row.appendChild(createCell(partner.phone));
    row.appendChild(createActionsCell(partner.partnerId));
    display.appendChild(row);
  });
};

document.addEventListener('DOMContentLoaded', function () {
  partnerForm = document.querySelector('#partnerForm');
  partnerForm.addEventListener('submit', savePartner);
  indexPartners();
});
