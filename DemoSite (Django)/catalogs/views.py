
from django.shortcuts import render,get_object_or_404

from .models import Catalog, Procedure
# Create your views here.

def catalog_list(request):
    catalogs = Catalog.objects.all()
    return render(request,'catalogs/catalog_list.html', {'catalogs': catalogs})


def catalog_detail(request, pk):
    catalog = get_object_or_404(Catalog,pk=pk)
    return render(request, 'catalogs/catalog_detail.html',{'catalog':catalog})

def procedure_detail(request, catalog_pk, procedure_pk):
    procedure = get_object_or_404(Procedure,catalog_id=catalog_pk,pk=procedure_pk)
    return render(request, 'catalogs/procedure_detail.html', {'procedure':procedure})