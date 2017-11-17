from django.test import TestCase
from django.utils import timezone
# Create your tests here.

from .models import Catalog,Procedure

class CatalogModelTests(TestCase):
    def test_catalog_creation(self):
        catalog = Catalog.objects.create(
            name="Test how Django unchained himself",
            description="learn to write some code will ya "
            )
        now = timezone.now()
        self.assertLessEqual(catalog.created_at,now)
        
class ProcedureModelTests(TestCase):
    def test_Procedure_creation(self):

        procedure=Procedure.objects.create(
            title="2nd test",
            description="learn to write some code will ya ",
            content="d",
            order=0,
            catalog=Catalog(),
            )
        now = timezone.now()
        self.assertLessEqual(procedure.created_at,now)       